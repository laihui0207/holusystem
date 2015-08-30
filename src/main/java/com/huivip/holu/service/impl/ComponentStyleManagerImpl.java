package com.huivip.holu.service.impl;

import com.huivip.holu.dao.*;
import com.huivip.holu.model.*;
import com.huivip.holu.service.ComponentManager;
import com.huivip.holu.service.ComponentStyleManager;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("componentStyleManager")
@WebService(serviceName = "ComponentStyleService", endpointInterface = "com.huivip.holu.service.ComponentStyleManager")
public class ComponentStyleManagerImpl extends GenericManagerImpl<ComponentStyle, Long> implements ComponentStyleManager {
    ComponentStyleDao componentStyleDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ProcessMidDao processMidDao;
    @Autowired
    CompanyDatabaseIndexDao companyDatabaseIndexDao;
    @Autowired
    ProjectDao projectDao;
    @Autowired
    ComponentManager componentManager;


    @Autowired
    public ComponentStyleManagerImpl(ComponentStyleDao componentStyleDao) {
        super(componentStyleDao);
        this.componentStyleDao = componentStyleDao;
    }

    @Override
    public List<ComponentStyle> getComponentStypeListByCompany(String companyId) {
        return componentStyleDao.getComponentStypeListByCompany(companyId);
    }

    @Override
    public List<ComponentStyle> getProcessListByCompanyAndStyleName(String styleID, String companyId, String userId,String componentId) {

        return getProcessListByCompanyAndStyleName(styleID,companyId,userId,componentId,null);
    }

    @Override
    public List<ComponentStyle> getProcessListByCompanyAndStyleName(String styleID, String companyId, String userId,String componentID, ExtendedPaginatedList list) {
        List<ComponentStyle> componentStyles = componentStyleDao.getProcessListByCompanyAndStyleName(styleID, companyId, list);
        User user = userDao.getUserByUserID(userId);
        Set<Post> posts = user.getPosts();
        String processTableName=companyDatabaseIndexDao.getTableNameByCompanyAndTableStyle(companyId,"ProcessMidTable");
        for (ComponentStyle style : componentStyles) {
            if (!user.isAllowCreateProject()) {
                for (Post post : posts) {
                    if (style.getProcessDictionary().getProcessID().equalsIgnoreCase(post.getProcessDictionary().getProcessID())
                            && user.getCompany().getCompanyId().equalsIgnoreCase(style.getCompany().getCompanyId())) {
                        style.setOperationer(true);
                        break;
                    }
                }
                ProcessMid processMid=processMidDao.getProcessMid(componentID,style.getStyleProcessID(),processTableName);
                if(null!=processMid){
                    style.setConfirmDate(processMid.getCreateDate());
                    style.setConfirmer(processMid.getUser());
                    style.setOperationer(false);
                }
                else {
                    break;
                }

            } else {
                ProcessMid processMid=processMidDao.getProcessMid(componentID,style.getStyleProcessID(),processTableName);
                if(null!=processMid){
                    style.setConfirmDate(processMid.getCreateDate());
                    style.setConfirmer(processMid.getUser());
                }
                style.setOperationer(true);
            }
        }
        return componentStyles;
    }

    @Override
    public ComponentStyle getComponentProcessByProcessID(String styleProcessID) {
        return componentStyleDao.getComponentProcessByProcessID(styleProcessID);
    }

    @Override
    public List<ComponentStyle> myTask(String userId) {
        User user=userDao.getUserByUserID(userId);
        List<Project> projectList=projectDao.getProjectByUserID(userId,"",null);
        List<Project> myProject=new ArrayList<>();
        for(Project project:projectList){
            if(project.getChildProjects()==null || project.getChildProjects().size()==0){
                myProject.add(project);
            }
            else {
                myProject.addAll(getAllMyProject(project.getChildProjects()));
            }
        }
        List<Component> components=new ArrayList<>();
        for(Project p:myProject){
            List<Component> list=componentManager.listComponentByProject(p.getProjectID(),userId,null);
            if(list!=null){
                components.addAll(list);
            }
        }

        List<ComponentStyle> result=new ArrayList<>();
        List<Task> tasks=new ArrayList<>();
        for(Component component: components){
            List<ComponentStyle> list=getProcessListByCompanyAndStyleName(component.getStyleID(),user.getCompany().getCompanyId(),userId,component.getComponentID());
            for(ComponentStyle style:list){
                if(style.isOperationer()){
                    Task task=new Task();
                    task.setComponent(component);

                    result.add(style);
                }
            }
        }

        return result;
    }
    private Set<Project> getAllMyProject(Set<Project> list){
        Set<Project> result=new HashSet<>();
        if(list==null || list.size()==0 ) return result;
        for(Project p: list){
            if(p.getChildProjects()==null || p.getChildProjects().size()==0){
                result.add(p);
            }
            else {
                result.addAll(getAllMyProject(p.getChildProjects()));
            }
        }
        return result;
    }
    @Override
    public List<Task> getMyTask(String userId){
        List<Task> tasks=new ArrayList<>();
        List<Project> projectList=projectDao.getProjectByUserID(userId,"",null);
        handleProject(new HashSet<>(projectList),userId,tasks);
        return tasks;
    }

    private void handleProject(Set<Project> projects,String userId,List<Task> tasks){
        if(null==projects) return;
        for(Project project:projects){
            if(project.getChildProjects()==null || project.getChildProjects().size()==0){
                handleComponents(project,userId,tasks);
            }
            else {
                handleProject(project.getChildProjects(), userId, tasks);
            }
        }
    }

    private void handleComponents(Project project,String userId,List<Task> tasks){
        if(project==null) return;
        List<Component> components=componentManager.listComponentByProject(project.getProjectID(),userId,null);
        for(Component component:components){
            if(component.getSubComponentListSet()==null || component.getSubComponentListSet().size()==0){
                Task task=new Task();
                task.setComponent(component);
                task.setComponentType("parent");
                handleComponentStyle(component, userId, task);
                tasks.add(task);
            }
            else {
                Set<SubComponentList> subComponentLists=component.getSubComponentListSet();
                for(SubComponentList subComponentList:subComponentLists){
                    Task task=new Task();
                    task.setComponent(component);
                    task.setSubComponent(subComponentList);
                    task.setComponentType("sub");
                    handleComponentStyle(component,userId,task);
                    tasks.add(task);
                }
            }
        }
    }

    private void handleComponentStyle(Component component, String userId, Task task) {
        if (null == component) return;
        User user=userDao.getUserByUserID(userId);
        List<ComponentStyle> componentStyles=getProcessListByCompanyAndStyleName(component.getStyleID(),user.getCompany().getCompanyId(),userId,component.getComponentID());
        for(ComponentStyle style: componentStyles){
            if(style.isOperationer()){
                task.setComponentStyle(style);
            }
        }
    }
}
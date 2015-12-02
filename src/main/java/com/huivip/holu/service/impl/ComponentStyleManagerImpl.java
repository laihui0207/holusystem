package com.huivip.holu.service.impl;

import com.huivip.holu.dao.*;
import com.huivip.holu.model.*;
import com.huivip.holu.service.*;
import com.huivip.holu.util.cache.Cache2kProvider;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.cache2k.Cache;
import org.cache2k.CacheBuilder;
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
    Cache<String,List<ComponentStyle>> cache= Cache2kProvider.getinstance().getListCache();
    Cache<String,ProcessMid> processMidCache=Cache2kProvider.getinstance().setCache(ProcessMid.class,
            CacheBuilder.newCache(String.class,ProcessMid.class).build());
    ComponentStyleDao componentStyleDao;
    @Autowired
    UserDao userDao;
    @Autowired
    UserManager userManager;
    @Autowired
    ProcessMidDao processMidDao;
    @Autowired
    CompanyDatabaseIndexDao companyDatabaseIndexDao;
    @Autowired
    CompanyDatabaseIndexManager companyDatabaseIndexManager;
    @Autowired
    ProjectDao projectDao;
    @Autowired
    ComponentManager componentManager;
    @Autowired
    ProcessMidManager processMidManager;


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
    public List<ComponentStyle> getProcessListByCompanyAndStyleName(String styleID, String userId, String componentId) {
        User user=userManager.getUserByUserID(userId);
        return getProcessListByCompanyAndStyleName(styleID, user,componentId,null);
    }

    @Override
    public List<ComponentStyle> getProcessListByCompanyAndStyleName(String styleID, User user, String componentID, ExtendedPaginatedList list) {
/*        String processTableName=companyDatabaseIndexManager.getProcessMidTableNameByCompany(user.getCompany().getCompanyId());*/
        String cacheKey="ComponentStyle_"+styleID+"_"+user.getCompany().getCompanyId();
        List<ComponentStyle> componentStyles = cache.peek(cacheKey);
        if(componentStyles==null){
            componentStyles=componentStyleDao.getProcessListByCompanyAndStyleName(styleID, user.getCompany().getCompanyId(), list);
            cache.put(cacheKey,componentStyles);
        }

        Set<Post> posts = user.getPosts();


        for (ComponentStyle style : componentStyles) {
            /*ProcessMid processMid=processMidDao.getProcessMid(componentID, style.getStyleProcessID(), processTableName);
            if(null!=processMid){
                style.setConfirmDate(processMid.getCreateDate());
                style.setConfirmer(processMid.getUser());
                style.setOperationer(false);
                style.setProcessMid(processMid);
            }*/
            if (!user.isAllowCreateProject()) {
                for (Post post : posts) {
                    if (post.getProcessDictionary()!=null && style.getProcessDictionary().getProcessID().equalsIgnoreCase(post.getProcessDictionary().getProcessID())
                            && user.getCompany().getCompanyId().equalsIgnoreCase(style.getCompany().getCompanyId())) {
                        style.setOperationer(true);
                        break;
                    }
                }

            } else {
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
        User user=userManager.getUserByUserID(userId);
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
        List<Mission> missions =new ArrayList<>();
        for(Component component: components){
            List<ComponentStyle> list=getProcessListByCompanyAndStyleName(component.getStyleID(), user,component.getComponentID(),null);
            for(ComponentStyle style:list){
                if(style.isOperationer()){
                    Mission mission =new Mission();
                    mission.setComponent(component);

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
    public List<Mission> getMyTask(String userId, String projectID){
        List<Mission> missions =new ArrayList<>();
        Project project=projectDao.getProjectByprojectID(projectID);
        User user=userManager.getUserByUserID(userId);
        handleComponents(project,user, missions);
        return missions;
    }

    @Override
    public List<ComponentStyle> getProcessListBySubComponent(String styleID, String userId, String subComponentID) {
        User user=userManager.getUserByUserID(userId);
        String processTableName=companyDatabaseIndexManager.getProcessMidTableNameByCompany(user.getCompany().getCompanyId());
        String cacheKey="ComponentStyle_"+styleID+"_"+user.getCompany().getCompanyId();
        List<ComponentStyle> componentStyles = cache.peek(cacheKey);
        if(componentStyles==null){
            componentStyles=componentStyleDao.getProcessListByCompanyAndStyleName(styleID, user.getCompany().getCompanyId(), null);
            cache.put(cacheKey,componentStyles);
        }

        Set<Post> posts = user.getPosts();


        for (ComponentStyle style : componentStyles) {
            ProcessMid processMid=processMidDao.getProcessMid(subComponentID, style.getStyleProcessID(), processTableName);
            if(null!=processMid){
                style.setOperationer(false);
                style.setProcessMid(processMid);
            }
            if (!user.isAllowCreateProject()) {
                for (Post post : posts) {
                    if (post.getProcessDictionary()!=null && style.getProcessDictionary().getProcessID().equalsIgnoreCase(post.getProcessDictionary().getProcessID())
                            && user.getCompany().getCompanyId().equalsIgnoreCase(style.getCompany().getCompanyId())) {
                        style.setOperationer(true);
                        break;
                    }
                }

            } else {
                style.setOperationer(true);
            }
        }
        return componentStyles;
    }

    private void handleProject(Set<Project> projects,String userId,List<Mission> missions){
        if(null==projects) return;
        User user=userManager.getUserByUserID(userId);
        for(Project project:projects){
            if(project.getChildProjects()==null || project.getChildProjects().size()==0){
                handleComponents(project,user, missions);
            }
            else {
                handleProject(project.getChildProjects(), userId, missions);
            }
        }
    }

    private void handleComponents(Project project,User user,List<Mission> missions){
        if(project==null) return;
        List<Component> components=componentManager.listComponentByProject(project.getProjectID(), user.getUserID(),null);
        for(Component component:components){
            if(component.getSubComponentListSet()==null || component.getSubComponentListSet().size()==0){
                Mission mission =new Mission();
                //mission.setComponent(component);
                mission.setProjectPathName(component.getProject().getProjectPathName());
                mission.setProjectID(component.getProject().getProjectID());
                mission.setComponentId(component.getComponentID());
                mission.setComponentName(component.getComponentName());
                mission.setComponentType("parent");
                handleComponentStyle(component,component.getComponentID(), user, mission);
                if(mission.getComponentStyle()!=null){
                    missions.add(mission);
                }
            }
            else {
                Set<SubComponentList> subComponentLists=component.getSubComponentListSet();
                for(SubComponentList subComponentList:subComponentLists){
                    Mission mission =new Mission();
                    //mission.setComponent(component);
                    mission.setProjectPathName(component.getProject().getProjectPathName());
                    mission.setProjectID(component.getProject().getProjectID());
                    mission.setComponentId(component.getComponentID());
                    mission.setComponentName(component.getComponentName());
                    //mission.setSubComponent(subComponentList);
                    mission.setSubComponentName(subComponentList.getSubComponentName());
                    mission.setSubComponentID(subComponentList.getSubComponentID());
                    mission.setComponentType("sub");
                    handleComponentStyle(component,subComponentList.getSubComponentID(),user, mission);
                    if(mission.getComponentStyle()!=null){
                        missions.add(mission);
                    }
                }
            }
        }
    }

    private void handleComponentStyle(Component component, String subcomponentID,User user, Mission mission) {
        if (null == component) return;
        List<ComponentStyle> componentStyles=getProcessListByCompanyAndStyleName(component.getStyleID(), user,component.getComponentID(),null);
        for(ComponentStyle style: componentStyles){
            if(style.isOperationer()){
                mission.setComponentStyle(style);
                ProcessMid processMid=processMidManager.getProcessMid2(subcomponentID, style.getStyleProcessID(), user.getCompany().getCompanyId());
                mission.setProcessMid(processMid);
            }
        }
    }
}
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
import javax.ws.rs.DefaultValue;
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
    SubComponentListManager subComponentListManager;


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
        String cacheKey="ComponentStyle_"+styleID+"_"+user.getCompany().getCompanyId()+"_"+user.getUserID();
        List<ComponentStyle> componentStyles = /*cache.peek(cacheKey);
        if(componentStyles==null){
            componentStyles=*/componentStyleDao.getProcessListByCompanyAndStyleName(styleID, user.getCompany().getCompanyId(), list);
           /* cache.put(cacheKey,componentStyles);
        }*/

        Set<Post> posts = user.getPosts();


        for (ComponentStyle style : componentStyles) {
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
    public List<Mission> getMyTask(String userId, String projectID, String taskType){
        List<Mission> missions =new ArrayList<>();
        Project project=projectDao.getProjectByprojectID(projectID);
        User user=userManager.getUserByUserID(userId);
        handleComponents(project,user, missions,taskType );
        return missions;
    }

    @Override
    public List<Mission> getTaskByComponentList(String userID, String componentList, String taskType) {
        List<Mission> missions =new ArrayList<>();
        String[] components=componentList.split(",");
        User user=userManager.getUserByUserID(userID);
        for(String componentID:components){
            Component component=componentManager.getComponentByComponentID(componentID,userID);
            if(component==null) continue;
            List<SubComponentList> subComponentLists = subComponentListManager.getSubComponentListByComponentID(componentID,userID,null);
            if(subComponentLists==null) continue;
            for (SubComponentList subComponentList : subComponentLists) {
                handleComponentStyle(component,subComponentList,user,taskType,missions);
            }
        }
        return missions;
    }

    @Override
    public List<ComponentStyle> getProcessListBySubComponent(String styleID, String userId, String subComponentID) {
        User user=userManager.getUserByUserID(userId);
        String processTableName=companyDatabaseIndexManager.getProcessMidTableNameByCompany(user.getCompany().getCompanyId());
        List<ComponentStyle> componentStyles=componentStyleDao.getProcessListByCompanyAndStyleName(styleID, user.getCompany().getCompanyId(), null);

        Set<Post> posts = user.getPosts();
        String postOfProcess="";
        for(Post post:posts){
            if(post.getProcessDictionary()!=null) {
                postOfProcess += post.getProcessDictionary().getProcessID();
            }
        }
        boolean preProcessFinished=true;
        int processOrder=0;
        for (ComponentStyle style : componentStyles) {
            ProcessMid processMid=processMidDao.getProcessMid(subComponentID, style.getStyleProcessID(), processTableName);
            style.setOperationer(false);
            if(null!=processMid){
                style.setProcessMid(processMid);

            }
            if (!user.isAllowCreateProject()) {
                    if ( postOfProcess.indexOf(style.getProcessDictionary().getProcessID().toString())>-1
                            && user.getCompany().getCompanyId().equalsIgnoreCase(style.getCompany().getCompanyId())) {
                        style.setOperationer(true);
                    }
                if(processOrder==0){
                    if(processMid!=null && processMid.getEndDate()!=null){
                        preProcessFinished=true;
                    }
                    else {
                        break;
                    }
                }
                else if(preProcessFinished){
                    if(processMid!=null && processMid.getEndDate()!=null){
                        preProcessFinished=true;
                    }
                    else {
                        break;
                    }
                }
            } else {
                style.setOperationer(true);
            }
            processOrder++;
        }
        return componentStyles;
    }

    private void handleComponents(Project project, User user, List<Mission> missions, String taskType){
        if(project==null) return;
        List<Component> components=componentManager.listComponentByProject(project.getProjectID(), user.getUserID(),null);
        for (Component component : components) {
            Set<SubComponentList> subComponentLists = component.getSubComponentListSet();
            for (SubComponentList subComponentList : subComponentLists) {
                handleComponentStyle(component,subComponentList,user,taskType,missions);
            }
        }
    }

    private void handleComponentStyle(Component component,SubComponentList subComponentList,User user,String taskType, List<Mission> missions) {
        if (null == component) return;
        List<ComponentStyle> componentStyles=getProcessListByCompanyAndStyleName(component.getStyleID(), user,component.getComponentID(),null);
        boolean preProcessFinished=true;
        int processOrder=0;
        for (ComponentStyle style : componentStyles) {
            /*if(style.isOperationer()){*/
            boolean isMyProcess = false;
            ProcessMid processMid = processMidManager.getProcessMid2(subComponentList.getSubComponentID(), style.getStyleProcessID(), user.getCompany().getCompanyId());
            if(user.isAllowCreateProject()){
                isMyProcess=true;
            }
            else {
                if (processOrder == 0 ) {
                    if (style.isOperationer()) {
                        isMyProcess = true;
                    }
                    if (processMid.getEndDate()!=null){
                        preProcessFinished=true;
                    } else {
                        preProcessFinished=false;
                    }
                } else if (processOrder > 0 && preProcessFinished) {
                    if (style.isOperationer()) {
                        isMyProcess = true;
                    }
                    if (processMid.getEndDate()!=null){
                        preProcessFinished=true;
                    } else {
                        preProcessFinished=false;
                    }
                }
                processOrder++;
            }

            if (isMyProcess) {
                Mission mission = new Mission();
                mission.setProjectPathName(component.getProject().getProjectPathName());
                mission.setProjectID(component.getProject().getProjectID());
                mission.setComponentId(component.getComponentID());
                mission.setComponentName(component.getComponentName());
                mission.setSubComponentName(subComponentList.getSubComponentName());
                mission.setSubComponentID(subComponentList.getSubComponentID());
                mission.setComponentType("sub");

                if (taskType.equalsIgnoreCase("doing")) {
                    if (processMid == null || processMid.getStartDate() == null || processMid.getEndDate() == null) {
                        mission.setProcessMid(processMid);
                        mission.setComponentStyle(style);
                    }
                } else if (taskType.equalsIgnoreCase("finish")) {
                    if (processMid != null && processMid.getEndDate() != null) {
                        mission.setProcessMid(processMid);
                        mission.setComponentStyle(style);
                    }
                } else {
                    mission.setProcessMid(processMid);
                    mission.setComponentStyle(style);
                }
                if(mission.getComponentStyle()!=null){
                    missions.add(mission);
                }
            }
            else if(!preProcessFinished){
                break;
            }
        }
        /*}*/
    }
}
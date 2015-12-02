package com.huivip.holu.service.impl;

import com.huivip.holu.dao.TaskDao;
import com.huivip.holu.dao.UserDao;
import com.huivip.holu.model.*;
import com.huivip.holu.service.*;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import com.huivip.holu.webapp.helper.PaginatedListImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("taskManager")
@WebService(serviceName = "TaskService", endpointInterface = "com.huivip.holu.service.TaskManager")
public class TaskManagerImpl extends GenericManagerImpl<Task, Long> implements TaskManager {
    TaskDao taskDao;
    @Autowired
    UserDao userDao;
    @Autowired
    UserManager userManager;
    @Autowired
    CompanyDatabaseIndexManager companyDatabaseIndexManager;
    @Autowired
    ProjectManager projectManager;
    @Autowired
    SubComponentListManager subComponentListManager;
    @Autowired
    ComponentStyleManager componentStyleManager;
    @Autowired
    ProcessMidManager processMidManager;

    @Autowired
    public TaskManagerImpl(TaskDao taskDao) {
        super(taskDao);
        this.taskDao = taskDao;
    }

    @Override
    public List<Mission> getTaskOfUser(String userId,String page,String pageSize) {
        List<Mission> missions=new ArrayList<>();
        ExtendedPaginatedList list=new PaginatedListImpl();
        list.setIndex(Integer.parseInt(page));
        list.setPageSize(Integer.parseInt(pageSize));
        if(page.equals("0")&& pageSize.equals("0")){
            list=null;
        }

        User user=userManager.getUserByUserID(userId);
        String tableName=companyDatabaseIndexManager.getTableNameByCompanyAndTableStyle(user.getCompany().getCompanyId(),"TaskTable");
        Set<Post> posts=user.getPosts();
        List<String> myProcesses=new ArrayList<>();
        for(Post post: posts){
            if(post.getProcessDictionary()!=null){
                myProcesses.add(post.getProcessDictionary().getProcessID());
            }
        }
        List<String> myProjects=collectProject(user.getUserID(),null);
        String projects="";
        for(String project:myProjects){
            if(projects.length()==0){
                projects="'"+project+"'";
            }
            else {
                projects+=",'"+project+"'";
            }
        }
        String processes="";
        for(String process:myProcesses){
            if(processes.length()==0){
                processes="'"+process+"'";
            }
            else {
                processes+=",'"+process+"'";
            }
        }
       /* projects="'XM0000007','XM0000013'";
        processes="'GX0000003','GX0000001'";*/
        List<Task> myTasks=taskDao.getTaskofUser(projects,processes,tableName, list);
        if(null==myTasks) return missions;
        for(Task task:myTasks){
            String subComponents=task.getSubComponentIdList();
            String[] subs=subComponents.split(",");
            for(String subId:subs){
                SubComponentList subComponentList=subComponentListManager.getSubComponentBySubComponentID(subId,userId);
                Component component=subComponentListManager.getParentComponent(subComponentList.getSubComponentID(),userId);
                Mission mission=new Mission();
                //mission.setComponent(component);
                mission.setComponentName(component.getComponentName());
                mission.setComponentId(component.getComponentID());
                mission.setProjectPathName(component.getProject().getProjectPathName());
                mission.setProjectID(component.getProject().getProjectID());

                //mission.setSubComponent(subComponentList);
                mission.setSubComponentID(subComponentList.getSubComponentID());
                mission.setSubComponentName(subComponentList.getSubComponentName());
                mission.setComponentType("sub");
                mission.setUser(user);
                mission.setType(task.getTaskStyle());
                List<ComponentStyle> componentStyles=componentStyleManager.getProcessListByCompanyAndStyleName(component.getStyleID(), user, component.getComponentID(),null);
                for(ComponentStyle style: componentStyles){
                    if(style.isOperationer()){
                        ProcessMid processMid=processMidManager.getProcessMid2(subComponentList.getSubComponentID(), style.getStyleProcessID(), user.getCompany().getCompanyId());
                        mission.setProcessMid(processMid);
                        mission.setComponentStyle(style);
                    }
                }
                missions.add(mission);
            }
        }

        return missions;
    }

    private List<String> collectProject(String userId,String parent){
        List<String> projects=new ArrayList<>();
        List<Project> parentProject=projectManager.getProjectByUserID(userId,parent,null);
        for(Project project:parentProject){
            if(project.getChildProjects()!=null && project.getChildProjects().size()>0){
                for(Project project1:project.getChildProjects()){
                    List<String> lists=collectProject(userId,project1.getProjectID());
                    projects.addAll(lists);
                }
            }
            else {
                projects.add(project.getProjectID());
            }
        }
        return projects;
    }

}
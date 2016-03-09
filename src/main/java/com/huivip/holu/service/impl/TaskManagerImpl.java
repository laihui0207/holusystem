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
import java.util.*;

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
    public List<Mission> getTaskOfUser(String userId, String taskType, String page, String pageSize) {
        List<Mission> missions = new ArrayList<>();
        ExtendedPaginatedList list = new PaginatedListImpl();
        list.setIndex(Integer.parseInt(page));
        list.setPageSize(Integer.parseInt(pageSize));
        if (page.equals("0") && pageSize.equals("0")) {
            list = null;
        }

        User user = userManager.getUserByUserID(userId);
        String tableName = companyDatabaseIndexManager.getTableNameByCompanyAndTableStyle(user.getCompany().getCompanyId(), "TaskTable");
        Set<Post> posts = user.getPosts();
        List<String> myProcesses = new ArrayList<>();
        for (Post post : posts) {
            if (post.getProcessDictionary() != null) {
                myProcesses.add(post.getProcessDictionary().getProcessID());
            }
        }
        List<String> myProjects = collectProject(user.getUserID(), null);
        String projects = "";
        for (String project : myProjects) {
            if (projects.length() == 0) {
                projects = "'" + project + "'";
            } else {
                projects += ",'" + project + "'";
            }
        }
        String processes = "";
        for (String process : myProcesses) {
            if (processes.length() == 0) {
                processes = "'" + process + "'";
            } else {
                processes += ",'" + process + "'";
            }
        }
       /* projects="'XM0000007','XM0000013'";
        processes="'GX0000003','GX0000001'";*/
        List<Task> myTasks = taskDao.getTaskofUser(projects, processes, tableName,user.isAllowCreateProject() , list);
        if (null == myTasks) return missions;
        for (Task task : myTasks) {
            String subComponents = task.getSubComponentIdList();
            String[] subs = subComponents.split(",");
            for (String subId : subs) {
                SubComponentList subComponentList = subComponentListManager.getSubComponentBySubComponentID(subId, userId);
                Component component = subComponentListManager.getParentComponent(subComponentList.getSubComponentID(), userId);

                List<ComponentStyle> componentStyles = componentStyleManager.getProcessListByCompanyAndStyleName(component.getStyleID(), user, component.getComponentID(), null);
                for (ComponentStyle style : componentStyles) {
                    if (style.isOperationer()) {
                        Mission mission = new Mission();
                        //mission.setComponent(component);
                        mission.setComponentName(component.getComponentName());
                        mission.setComponentId(component.getComponentID());
                        mission.setProjectPathName(component.getProject().getProjectPathName());
                        mission.setProjectID(component.getProject().getProjectID());

                        //mission.setSubComponent(subComponentList);
                        mission.setSubComponentID(subComponentList.getSubComponentID());
                        mission.setSubComponentName(subComponentList.getSubComponentName());
                        mission.setComponentType("sub");
                        //mission.setUser(user);
                        mission.setType(task.getTaskStyle());
                        ProcessMid processMid = processMidManager.getProcessMid2(subComponentList.getSubComponentID(), style.getStyleProcessID(), user.getCompany().getCompanyId());
                        if (taskType.equalsIgnoreCase("doing")) {
                            if (processMid==null || processMid.getStartDate() == null || processMid.getEndDate() == null) {
                                mission.setProcessMid(processMid);
                                mission.setComponentStyle(style);
                            }
                        } else if (taskType.equalsIgnoreCase("finish")) {
                            if (processMid!=null && processMid.getEndDate() != null) {
                                mission.setProcessMid(processMid);
                                mission.setComponentStyle(style);
                            }
                        }
                        else {
                            mission.setProcessMid(processMid);
                            mission.setComponentStyle(style);
                        }
                        if(mission.getComponentStyle()!=null){
                            missions.add(mission);
                        }
                    }

                }

            }
        }

            return missions;
        }

    @Override
    public List<String> getSubComponentOfTask(String userId) {
        List<String> subComponentLists=new ArrayList<>();
        User user = userManager.getUserByUserID(userId);
        String tableName = companyDatabaseIndexManager.getTableNameByCompanyAndTableStyle(user.getCompany().getCompanyId(), "TaskTable");
        Set<Post> posts = user.getPosts();
        List<String> myProcesses = new ArrayList<>();
        for (Post post : posts) {
            if (post.getProcessDictionary() != null) {
                myProcesses.add(post.getProcessDictionary().getProcessID());
            }
        }
        List<String> myProjects = collectProject(user.getUserID(), null);
        String projects = "";
        for (String project : myProjects) {
            if (projects.length() == 0) {
                projects = "'" + project + "'";
            } else {
                projects += ",'" + project + "'";
            }
        }
        String processes = "";
        for (String process : myProcesses) {
            if (processes.length() == 0) {
                processes = "'" + process + "'";
            } else {
                processes += ",'" + process + "'";
            }
        }
       /* projects="'XM0000007','XM0000013'";
        processes="'GX0000003','GX0000001'";*/
        List<Task> myTasks = taskDao.getTaskofUser(projects, processes, tableName,user.isAllowCreateProject(), null);
        Set<String> subSet=new HashSet<>();
        if(myTasks==null) return subComponentLists;
        for (Task task : myTasks) {
            String subComponents = task.getSubComponentIdList();
            if(subComponents!=null){
                String[] subs = subComponents.split(",");
                for (String subId : subs) {
                    subSet.add(subId);
                    //subComponentLists.add(subId);
                }
            }
        }
        if(subSet.size()>0){
            subComponentLists.addAll(subSet);
        }
        return subComponentLists;
    }

    @Override
    public List<Mission> getTaskBySubComponent(String userId, String subId,String taskType) {
        User user=userManager.getUserByUserID(userId);
        List<Mission> missions=new ArrayList<>();
        SubComponentList subComponentList = subComponentListManager.getSubComponentBySubComponentID(subId, userId);
        Component component = subComponentListManager.getParentComponent(subComponentList.getSubComponentID(), userId);

        List<ComponentStyle> componentStyles = componentStyleManager.getProcessListByCompanyAndStyleName(component.getStyleID(), user, component.getComponentID(), null);
        for (ComponentStyle style : componentStyles) {
            if (style.isOperationer()) {
                Mission mission = new Mission();
                //mission.setComponent(component);
                mission.setComponentName(component.getComponentName());
                mission.setComponentId(component.getComponentID());
                mission.setProjectPathName(component.getProject().getProjectPathName());
                mission.setProjectID(component.getProject().getProjectID());

                //mission.setSubComponent(subComponentList);
                mission.setSubComponentID(subComponentList.getSubComponentID());
                mission.setSubComponentName(subComponentList.getSubComponentName());
                mission.setComponentType("sub");
                //mission.setType(task.getTaskStyle());
                //mission.setUser(user);
                ProcessMid processMid = processMidManager.getProcessMid2(subComponentList.getSubComponentID(), style.getStyleProcessID(), user.getCompany().getCompanyId());
                if (taskType.equalsIgnoreCase("doing")) {
                    if (processMid==null || processMid.getStartDate() == null || processMid.getEndDate() == null) {
                        mission.setProcessMid(processMid);
                        mission.setComponentStyle(style);
                    }
                } else if (taskType.equalsIgnoreCase("finish")) {
                    if (processMid!=null && processMid.getEndDate() != null) {
                        mission.setProcessMid(processMid);
                        mission.setComponentStyle(style);
                    }
                }
                else {
                    mission.setProcessMid(processMid);
                    mission.setComponentStyle(style);
                }
                if(mission.getComponentStyle()!=null){
                    missions.add(mission);
                }
            }

        }

        return missions;
    }

    @Override
    public List<LabelValue> getProjects(String userID,String taskType) {
        List<LabelValue> result=new ArrayList<>();
        User user = userManager.getUserByUserID(userID);
        String tableName = companyDatabaseIndexManager.getTableNameByCompanyAndTableStyle(user.getCompany().getCompanyId(), "TaskTable");
        List<Object[]> projects=taskDao.getProject(tableName,taskType, userID);
        if(projects==null || projects.size()==0) return  result;
        for(Object[] objs:projects){
            LabelValue lv=new LabelValue((String)objs[1],(String)objs[0]);
            result.add(lv);
        }
        return result;
    }

    @Override
    public List<LabelValue> getSubComponent(String userId, String projectID,String taskType) {
        List<LabelValue> result=new ArrayList<>();
        User user = userManager.getUserByUserID(userId);
        String tableName = companyDatabaseIndexManager.getTableNameByCompanyAndTableStyle(user.getCompany().getCompanyId(), "TaskTable");
        String processes = "";
        /*if (user.isAllowCreateProject()) {
            processes = null;
        } else {*/

            Set<Post> posts = user.getPosts();
            List<String> myProcesses = new ArrayList<>();
            for (Post post : posts) {
                if (post.getProcessDictionary() != null) {
                    myProcesses.add(post.getProcessDictionary().getProcessID());
                }
            }
            for (String process : myProcesses) {
                if (processes.length() == 0) {
                    processes = "'" + process + "'";
                } else {
                    processes += ",'" + process + "'";
                }
            }
       /* }*/
        List<Task> myTasks = taskDao.getTaskofUser("'"+projectID+"'", processes, tableName,user.isAllowCreateProject(), null);
        if(myTasks==null) return result;
        Set<LabelValue> resultSet=new HashSet<>();
        for (Task task : myTasks) {
            String subComponents = task.getSubComponentIdList();
            if(subComponents!=null){
                String[] subs = subComponents.split(",");
                for (String subId : subs) {
                    resultSet.add(new LabelValue(subComponentListManager.getSubComponetName(subId,user),subId));
                }
            }
        }
        result.addAll(resultSet);
        return result;
    }

    private List<String> collectProject (String userId, String parent){
            List<String> projects = new ArrayList<>();
            List<Project> parentProject = projectManager.getProjectByUserID(userId, parent, null);
            for (Project project : parentProject) {
                if (project.getChildProjects() != null && project.getChildProjects().size() > 0) {
                    for (Project project1 : project.getChildProjects()) {
                        List<String> lists = collectProject(userId, project1.getProjectID());
                        projects.addAll(lists);
                    }
                } else {
                    projects.add(project.getProjectID());
                }
            }
            return projects;
        }

    }
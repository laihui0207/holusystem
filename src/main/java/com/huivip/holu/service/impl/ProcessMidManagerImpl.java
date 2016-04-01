package com.huivip.holu.service.impl;

import com.huivip.holu.dao.ProcessMidDao;
import com.huivip.holu.dao.ProjectDao;
import com.huivip.holu.model.*;
import com.huivip.holu.service.*;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import com.huivip.holu.webapp.helper.PaginatedListImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service("processMidManager")
@WebService(serviceName = "ProcessMidService", endpointInterface = "com.huivip.holu.service.ProcessMidManager")
public class ProcessMidManagerImpl extends GenericManagerImpl<ProcessMid, Long> implements ProcessMidManager {
    private static final Log log = LogFactory.getLog(ProcessMidManagerImpl.class);
    ProcessMidDao processMidDao;
    @Autowired
    UserManager userManager;
    @Autowired
    CompanyDatabaseIndexManager companyDatabaseIndexManager;
    @Autowired
    ProjectDao projectDao;
    @Autowired
    ComponentManager componentManager;
    @Autowired
    ComponentStyleManager componentStyleManager;

    @Autowired
    public ProcessMidManagerImpl(ProcessMidDao processMidDao) {
        super(processMidDao);
        this.processMidDao = processMidDao;
    }

    @Override
    public ProcessMid save(ProcessMid object, String userID) {
        User user=userManager.getUserByUserID(userID);
        String tableName=companyDatabaseIndexManager.getProcessMidTableNameByCompany(user.getCompany().getCompanyId());
        return processMidDao.save(object,tableName);
    }

    @Override
    public ProcessMid getProcessMid(String componentID, String processID, String userID) {
        User user=userManager.getUserByUserID(userID);
        String tableName=companyDatabaseIndexManager.getProcessMidTableNameByCompany(user.getCompany().getCompanyId());
        return processMidDao.getProcessMid(componentID,processID,tableName);
    }

    @Override
    public ProcessMid getProcessMid2(String componentID, String processID, String companyID) {
        String tableName=companyDatabaseIndexManager.getProcessMidTableNameByCompany(companyID);
        return processMidDao.getProcessMid(componentID,processID,tableName);
    }

    @Override
    public List<LabelValue> getProjectOfUser(String userID,String taskType) {
        List<LabelValue> result=new ArrayList<>();
        /*User user=userManager.getUserByUserID(userID);
        String tableName=companyDatabaseIndexManager.getProcessMidTableNameByCompany(user.getCompany().getCompanyId());
        String userProcesses=collectProcessOfUser(user);
        List<Object[]> projects=processMidDao.getProjectListOfUser(taskType,userProcesses, tableName );*/
        List<Project> projects=projectDao.getProjectByUserID(userID,"",null);
        if(projects==null || projects.size()==0) return  result;
        for(Project project:projects){
            LabelValue lv=new LabelValue(project.getProjectPathName(),project.getProjectID());
            result.add(lv);
        }
        return result;
    }
    private String collectProcessOfUser(User user){
        if(user.isAllowCreateProject()){
            return null;
        }
        Set<Post> posts = user.getPosts();
        List<String> myProcesses = new ArrayList<>();
        for (Post post : posts) {
            if (post.getProcessDictionary() != null) {
                myProcesses.add(post.getProcessDictionary().getProcessID());
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
        return processes;
    }
    @Override
    public List<LabelValue> getComponentStyleOfProject(String projectID, String userId,String taskType) {
        List<LabelValue> result=new ArrayList<>();
        User user=userManager.getUserByUserID(userId);
        String userProcesses=collectProcessOfUser(user);
        String tableName=companyDatabaseIndexManager.getProcessMidTableNameByCompany(user.getCompany().getCompanyId());
        List<Object[]> componentStyles=processMidDao.getComponentStylesOfProject(projectID,taskType,userProcesses , tableName);
        if(componentStyles==null || componentStyles.size()==0) return result;
        for(Object[] objs:componentStyles){
            LabelValue lv=new LabelValue((String)objs[1],(String)objs[0]);
            result.add(lv);
        }
        return result;
    }

    @Override
    public List<Mission> getMyMissions(String projectID, String styleID, String userID, String type,String pageIndex,String pageSize) {
        List<Mission> missions =new ArrayList<>();
        /*Project project=projectDao.getProjectByprojectID(projectID);
        User user=userManager.getUserByUserID(userID);
        handleComponents(project,user, missions,type,styleID);*/
        // todo  check pre-process if confirm
        User user=userManager.getUserByUserID(userID);
        ExtendedPaginatedList list= new PaginatedListImpl();
        list.setIndex(Integer.parseInt(pageIndex));
        list.setPageSize(Integer.parseInt(pageSize));
       /* String userProcesses=null;//collectProcessOfUser(user);
        if(!user.isAllowCreateProject()){
           // userProcesses=collectProcessOfUser(user);
           list=null;
        }*/
        List<String> subComponents=processMidDao.getSubComponentOfMission(projectID,styleID,user.getCompany().getCompanyId(),type,list );
        if(subComponents==null || subComponents.size()==0) return missions;
        for(String subComponentID:subComponents) {

            List<Object[]> data = processMidDao.getMission(projectID, styleID, subComponentID, user.getCompany().getCompanyId());
            // data format: pm.subcomponentID,usb.SubComponentName,rp.ProjectPathName,pm.StyleProcessID,pm.processID,rc.ProcessName,uc.ComponentName,rc.StyleName,rc.processOrder,pm.startDate,pm.EndDate
            boolean preProcessFinished = true;
            for (Object[] objs : data) {
                Mission mission = new Mission();
                mission.setSubComponentID((String) objs[0]);
                mission.setSubComponentName((String) objs[1]);
                mission.setProjectPathName((String) objs[2]);
                mission.setProjectID(projectID);
                mission.setStyleProcessID((String) objs[3]);
                mission.setProcessName((String) objs[5]);
                mission.setStyleName((String) objs[7]);
                mission.setStartDate((Date) objs[9]);
                mission.setEndDate((Date) objs[10]);
                mission.setSubComponentList(subComponents);
                boolean ifFinished = mission.getEndDate() != null;
                if (!ifFinished && preProcessFinished) {
                    mission.setOwner(true);
                } else if (ifFinished) {
                    mission.setOwner(true);
                }
                preProcessFinished = ifFinished;
                if (user.isAllowCreateProject()) {
                    if (type.equalsIgnoreCase("finish") && ifFinished) {
                        missions.add(mission);
                    } else if (type.equalsIgnoreCase("doing") && !ifFinished) {
                        missions.add(mission);
                    } else if (type.equalsIgnoreCase("all")) {
                        missions.add(mission);
                    }
                } else {
                    for (Post post : user.getPosts()) {
                        if (post.getProcessDictionary() != null && ((String) objs[4]).equalsIgnoreCase(post.getProcessDictionary().getProcessID())) {
                            if (type.equalsIgnoreCase("finish") && ifFinished) {
                                missions.add(mission);
                            } else if (type.equalsIgnoreCase("doing") && !ifFinished) {
                                missions.add(mission);
                            } else if (type.equalsIgnoreCase("all")) {
                                missions.add(mission);
                            }
                            break;
                        }

                    }
                }
            }
        }
        return missions;
    }

    @Override
    public List<Mission> getSumComponentsOfMyMissions(String projectID, String styleID, String userID,String subComponentID, String type) {
        User user=userManager.getUserByUserID(userID);
        List<Mission> missions =new ArrayList<>();
        List<Object[]> data = processMidDao.getMission(projectID, styleID, subComponentID, user.getCompany().getCompanyId());
        // data format: pm.subcomponentID,usb.SubComponentName,rp.ProjectPathName,pm.StyleProcessID,pm.processID,rc.ProcessName,uc.ComponentName,rc.StyleName,rc.processOrder,pm.startDate,pm.EndDate
        boolean preProcessFinished = true;
        for (Object[] objs : data) {
            Mission mission = new Mission();
            mission.setSubComponentID((String) objs[0]);
            mission.setSubComponentName((String) objs[1]);
            mission.setProjectPathName((String) objs[2]);
            mission.setProjectID(projectID);
            mission.setStyleProcessID((String) objs[3]);
            mission.setProcessName((String) objs[5]);
            mission.setStyleName((String) objs[7]);
            mission.setStartDate((Date) objs[9]);
            mission.setEndDate((Date) objs[10]);
            boolean ifFinished = mission.getEndDate() != null;
            if (!ifFinished && preProcessFinished) {
                mission.setOwner(true);
            } else if (ifFinished) {
                mission.setOwner(true);
            }
            preProcessFinished = ifFinished;
            if (user.isAllowCreateProject()) {
                if (type.equalsIgnoreCase("finish") && ifFinished) {
                    missions.add(mission);
                } else if (type.equalsIgnoreCase("doing") && !ifFinished) {
                    missions.add(mission);
                } else if (type.equalsIgnoreCase("all")) {
                    missions.add(mission);
                }
            } else {
                for (Post post : user.getPosts()) {
                    if (post.getProcessDictionary() != null && ((String) objs[4]).equalsIgnoreCase(post.getProcessDictionary().getProcessID())) {
                        if (type.equalsIgnoreCase("finish") && ifFinished) {
                            missions.add(mission);
                        } else if (type.equalsIgnoreCase("doing") && !ifFinished) {
                            missions.add(mission);
                        } else if (type.equalsIgnoreCase("all")) {
                            missions.add(mission);
                        }
                        break;
                    }

                }
            }
        }
        return missions;
    }

    @Override
    public ProcessMid save(String subComponentID,String styleProcessID,String processNote,
                           String startDate,String endDate,String positionGPS,String positionName,String userID) {
        ProcessMid processMid = new ProcessMid();
        processMid.setCreateDate(new Date());
        processMid.setSubComponentID(subComponentID);
        processMid.setStyleProcessID(styleProcessID);
        processMid.setProcessNote(processNote);
        processMid.setPositionGPS(positionGPS);
        processMid.setPositionName(positionName);
        processMid.setUserID(userID);
        // to do  check if confirm again
        return save(processMid, userID);
    }

    @Override
    public List<ProcessMid> batchConfirm(String data,String type,String positionGPS,String positionname,String userID) {
        List<ProcessMid> result=new ArrayList<>();
        if(null==data || data.length()==0) return result;
        String[] datas=data.split(",");
        for(String ids:datas){
            //ids format: projectID, SubcomponentID,processID
            String[] idStrs=ids.split("#");
            String subcomponentID=idStrs[1];
            String processID=idStrs[2];
            String processNote="";
            if(type.equalsIgnoreCase("start")){
                ProcessMid orginalMid=getProcessMid(subcomponentID,processID,userID);
                if(orginalMid!=null){
                    processNote=orginalMid.getProcessNote();
                }
                ProcessMid mid=startConfirm(subcomponentID,processID,processNote,"","",positionGPS,positionname,userID);
                if(mid!=null){
                    result.add(mid);
                }
            }
            else if(type.equalsIgnoreCase("end")){
                ProcessMid mid=stopConfirm(subcomponentID,processID,processNote,"","",positionGPS,positionname,userID);
                if(mid!=null){
                    result.add(mid);
                }
            }
        }
        return result;
    }

    @Override
    public ProcessMid startConfirm( String subComponentID, String styleProcessID, String processNote,
                                    String startDate, String endDate, String positionGPS,String positionName, String userID) {
        ProcessMid processMid = new ProcessMid();
        processMid.setCreateDate(new Date());
        processMid.setSubComponentID(subComponentID);
        processMid.setStyleProcessID(styleProcessID);
        processMid.setProcessNote(processNote);
        processMid.setPositionGPS(positionGPS);
        processMid.setStartDate(new Date());
        processMid.setPositionName(positionName);
        processMid.setUserID(userID);
        // to do  check if confirm again
        log.info("User confirm start:"+subComponentID+","+styleProcessID+
                " on "+processMid.getStartDate()+" at "+processMid.getPositionName());
        return save(processMid, userID);
    }

    @Override
    public ProcessMid stopConfirm( String subComponentID,  String styleProcessID,  String processNote,
                                   String startDate, String endDate,  String positionGPS,String positionName, String userID) {
        ProcessMid processMid = new ProcessMid();
        processMid.setCreateDate(new Date());
        processMid.setSubComponentID(subComponentID);
        processMid.setStyleProcessID(styleProcessID);
        processMid.setProcessNote(processNote);
        processMid.setPositionGPS(positionGPS);
        processMid.setPositionName(positionName);
        processMid.setEndDate(new Date());
        processMid.setUserID(userID);
        log.info("User confirm end:"+subComponentID+","+styleProcessID+
                " on "+processMid.getEndDate()+" at "+processMid.getPositionName());
        // to do  check if confirm again
        return save(processMid, userID);
    }

    @Override
    public ProcessMid ConfirmQuestion( String subComponentID,  String styleProcessID,  String processNote,
                                       String startDate,  String endDate,  String positionGPS,String positionName,  String userID) {
        ProcessMid processMid = new ProcessMid();
        processMid.setCreateDate(new Date());
        processMid.setSubComponentID(subComponentID);
        processMid.setStyleProcessID(styleProcessID);
        processMid.setProcessNote(processNote);
        processMid.setPositionGPS(positionGPS);
        processMid.setPositionName(positionName);
        processMid.setUserID(userID);
        log.info("User send question:"+subComponentID+","+styleProcessID+
                " : "+processMid.getProcessNote()+" at "+processMid.getPositionName());
        // to do  check if confirm again
        return save(processMid, userID);
    }
    private void handleComponents(Project project, User user, List<Mission> missions, String taskType,String styleID){
        if(project==null) return;
        List<Component> components=componentManager.listComponentByProject(project.getProjectID(), user.getUserID(),null);
        for (Component component : components) {
            Set<SubComponentList> subComponentLists = component.getSubComponentListSet();
            for (SubComponentList subComponentList : subComponentLists) {
                handleComponentStyle(component,subComponentList,user,taskType,styleID,missions);
            }
        }
    }

    private void handleComponentStyle(Component component,SubComponentList subComponentList,User user,String taskType,String styleID, List<Mission> missions) {
        if (null == component) return;
        List<ComponentStyle> componentStyles=componentStyleManager.getProcessListByCompanyAndStyleName(component.getStyleID(), user,component.getComponentID(),null);
        boolean preProcessFinished=true;
        int processOrder=0;
        for (ComponentStyle style : componentStyles) {
            /*if(style.isOperationer()){*/
            boolean isMyProcess = false;
            if(!style.getStyleID().equalsIgnoreCase(styleID)) continue;
            ProcessMid processMid = getProcessMid2(subComponentList.getSubComponentID(), style.getStyleProcessID(), user.getCompany().getCompanyId());
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
    }
}
package com.huivip.holu.service.impl;

import com.huivip.holu.dao.SummaryTotalDao;
import com.huivip.holu.model.*;
import com.huivip.holu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("summaryTotalManager")
@WebService(serviceName = "SummaryTotalService", endpointInterface = "com.huivip.holu.service.SummaryTotalManager")
public class SummaryTotalManagerImpl extends GenericManagerImpl<SummaryTotal, Long> implements SummaryTotalManager {
    SummaryTotalDao summaryTotalDao;
    @Autowired
    UserManager userManager;
    @Autowired
    CompanyDatabaseIndexManager companyDatabaseIndexManager;
    @Autowired
    SettingManager settingManager;
    @Autowired
    ProjectManager projectManager;
    @Autowired
    DepartmentManager departmentManager;


    @Autowired
    public SummaryTotalManagerImpl(SummaryTotalDao summaryTotalDao) {
        super(summaryTotalDao);
        this.summaryTotalDao = summaryTotalDao;
    }

    @Override
    public List<SummaryItem> getHomePageSummary(String userID, String ItemStyle, String sumDate, String startOrEnd) {
        List<SummaryItem> result=new ArrayList<>();
        User user=userManager.getUserByUserID(userID);
        String tableName=companyDatabaseIndexManager.getTableNameByCompanyAndTableStyle(user.getCompany().getCompanyId(),"SummaryTotalTable");

        String processes=getHomeProcessIds(userID);
        if(processes==null || processes.equalsIgnoreCase("")) return result;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String searchDateEnd=simpleDateFormat.format(new Date());
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-");
        String searchDateStart=simpleDateFormat1.format(new Date())+"01";

/*        searchDateString="2015-09-08";*/
        //a.processId,a.processName,b.SumWeight_actual,a.SumWeight_plan f
        List<Object[]> items=summaryTotalDao.getSummaryItem(searchDateStart, searchDateEnd, processes, ItemStyle, startOrEnd, tableName);
        for(Object[] objs:items){
            SummaryItem item=convertSummaryItem(objs,ItemStyle);
            item.setSumDate(new Date());
            result.add(item);
        }
        return result;
    }

    @Override
    public HashMap<String, List<SummaryItem>> getHomePageDetailSummary(String userID, String ItemStyle, String sumDate, String startOrEnd) {
        HashMap<String,List<SummaryItem>> result=new HashMap<>();
        User user=userManager.getUserByUserID(userID);
        String tableName=companyDatabaseIndexManager.getTableNameByCompanyAndTableStyle(user.getCompany().getCompanyId(),"SummaryTotalTable");

        String processes=getHomeProcessIds(userID);
        if(processes==null || processes.equalsIgnoreCase("")) return result;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String searchDateEnd=simpleDateFormat.format(new Date());
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-");
        String searchDateStart=simpleDateFormat1.format(new Date())+"01";
        List<String> validItem=summaryTotalDao.getSummaryValidItem(searchDateStart,searchDateEnd , processes, ItemStyle, startOrEnd, tableName);
        if(null==validItem || validItem.size()==0) return result;
        for(String item:validItem){
            List<Object[]> itemSummary=summaryTotalDao.getSummaryDetail(item, searchDateStart, searchDateEnd, processes, ItemStyle, startOrEnd, tableName);
            List<SummaryItem> list=new ArrayList<>();
            for(Object[] objs:itemSummary){
             SummaryItem summaryItem=convertSummaryItem(objs,ItemStyle);
                summaryItem.setSumDate(new Date());
                list.add(summaryItem);
            }
            result.put(item,list);
        }
        return result;
    }

    @Override
    public HashMap<String, List<SummaryItem>> getSummaryDetail(String userID, String itemName, String ItemStyle, String sumDate, String startOrEnd) {
        HashMap<String,List<SummaryItem>> result=new HashMap<>();
        User user=userManager.getUserByUserID(userID);
        String summaryTableName=companyDatabaseIndexManager.getTableNameByCompanyAndTableStyle(user.getCompany().getCompanyId(),"SummaryTotalTable");
        String processes=getHomeProcessIds(userID);
        if(processes==null || processes.equalsIgnoreCase("")) return result;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String searchDateEnd=simpleDateFormat.format(new Date());
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-");
        String searchDateStart=simpleDateFormat1.format(new Date())+"01";
        Date searchDate=getSumDate(sumDate);
        String searchDateString=simpleDateFormat.format(searchDate);
/*        searchDateString="2015-09-08";*/
        if(ItemStyle.equalsIgnoreCase("project")){
/*            List<String> validItem=summaryTotalDao.getSummaryValidItem(searchDateStart, searchDateEnd, processes, ItemStyle, startOrEnd, summaryTableName);*/
/*            if(null==validItem || validItem.size()==0) return result;*/
/*            for(String item:validItem){*/
                List<Object[]> itemDetail=summaryTotalDao.getSummaryDetailByItem(itemName, searchDateStart, searchDateEnd, processes, ItemStyle, startOrEnd,summaryTableName);
                List<SummaryItem> list=new ArrayList<>();
                for(Object[] objs:itemDetail){
                    SummaryItem summaryItem=convertSummaryItem(objs, ItemStyle);
                    summaryItem.setSumDate(searchDate);
                    list.add(summaryItem);
                }
                result.put(itemName,list);
            /*}*/
        }
        /*else if(ItemStyle.equalsIgnoreCase("factory")){
            List<String> validItem=summaryTotalDao.getDetailValidFactoryItem(itemID, searchDateString, processes, startOrEnd, summaryTableName);
            if(null==validItem || validItem.size()==0) return result;
            for(String item:validItem){
                List<Object[]> itemDetail=summaryTotalDao.getDetailFactoryDataByItem(itemID, item, searchDateString, processes, startOrEnd, summaryTableName);
                List<SummaryItem> list=new ArrayList<>();
                for(Object[] objs:itemDetail){
                    SummaryItem summaryItem=convertSummaryItem(objs,ItemStyle);
                    summaryItem.setSumDate(searchDate);
                    list.add(summaryItem);
                }
                result.put(item,list);
            }
        }*/
        return result;
    }

    @Override
    public List<SummaryProcess> getSummaryProcess(String userID, String itemStyle) {
        User user=userManager.getUserByUserID(userID);
        String tableName=companyDatabaseIndexManager.getTableNameByCompanyAndTableStyle(user.getCompany().getCompanyId(),"SummaryDateTable");
        List<Object[]> list=new ArrayList<>();
        if(itemStyle.equalsIgnoreCase("project")){
             list=summaryTotalDao.getProjectSummaryProcess(tableName);
        }
        else if (itemStyle.equalsIgnoreCase("factory")){
            list=summaryTotalDao.getFactorySummaryProcess(tableName);
        }
        List<SummaryProcess> result=new ArrayList<>();
        for(Object[] objects:list){
            result.add(convertSummaryProcess(objects));
        }
        return result;
    }

    @Override
    public HashMap<String,List<SummaryProcess>> getSummaryProcessDetail(String userID, String itemStyle, String itemId) {
        User user=userManager.getUserByUserID(userID);
        String tableName=companyDatabaseIndexManager.getTableNameByCompanyAndTableStyle(user.getCompany().getCompanyId(),"SummaryDateTable");
        String itemName="";
        HashMap<String,List<SummaryProcess>> result=new HashMap<>();
        if(itemStyle.equalsIgnoreCase("project")){
            Project project=projectManager.getProjectByprojectID(itemId);
            itemName=project.getProjectPathName();
            List<Object[]> list=summaryTotalDao.getProjectSummaryProcessDetail(tableName,itemId);
            List<SummaryProcess> processes=new ArrayList<>();
            for(Object[] objs:list){
                processes.add(convertSummaryProcess(objs));
            }
            result.put(itemName,processes);
        }
        else if(itemStyle.equalsIgnoreCase("factory")){
            Department department=departmentManager.getDepartmentByDepartmentID(itemId);
            itemName=department.getName();
            List<SummaryProcess> processes=new ArrayList<>();
            List<Object[]> list=summaryTotalDao.getFactorySummaryProcessDetail(tableName,itemId);
            for(Object[] objs:list){
                processes.add(convertSummaryProcess(objs));
            }
            result.put(itemName,processes);
        }

        return result;
    }

    private String getHomeProcessIds(String userID){
        User user=userManager.getUserByUserID(userID);
        Setting setting=settingManager.getSettingBySearch(user.getCompany().getCompanyId(),"HomePage","HomePageProcessDisplayList");
        String processIds=setting.getKeyValue();
        if(null==processIds || processIds.length()==0) return "";
        String[] processIdArray=processIds.split(",");
        String processes="";
        for(String p:processIdArray) {
            if (processes.equalsIgnoreCase("")) {
                processes += "'" + p + "'";
            } else {
                processes +=",'"+p+"'";
            }
        }
        return processes;
    }
    private Date getSumDate(String sumDate){
        if(sumDate.equalsIgnoreCase("today")){
            return new Date();
        }
        else if(sumDate.equalsIgnoreCase("yesterday")){
            Calendar today= Calendar.getInstance();
            today.setTime(new Date());
            today.set(Calendar.DAY_OF_MONTH,today.get(Calendar.DAY_OF_MONTH)-1);
            return today.getTime();
        }
        return new Date();
    }
    private SummaryItem convertSummaryItem(Object[] objs,String style){
        SummaryItem item=new SummaryItem();

        if(objs[0]==null){
            item.setActual(0);
        }
        else {
            item.setActual((Double) objs[0]);
        }
        if(objs[1]==null){
            item.setPlan(0);
        }
        else {
            item.setPlan((Double) objs[1]);
        }
        if(objs.length >2 && objs[2]!=null) {
            item.setProcessID((String) objs[2]);
        }
        if (objs.length >3 && objs[3] !=null) {
            item.setItemName((String) objs[3]);
        }
        if (objs.length >4 &&objs[4] !=null) {
            item.setItemID((String) objs[4]);
        }
        item.setItemStyle(style);
        return item;
    }
    private SummaryProcess convertSummaryProcess(Object[] objs){
        //a.ItemName,r.ProjectName,SumDate_actual_start,SumDate_actual_end,SumDate_plan_start,SumDate_plan_end,SumDate_predict_start,SumDate_predict_end
        SummaryProcess process=new SummaryProcess();
        process.setItemID((String) objs[0]);
        process.setItemName((String) objs[1]);
        if(objs[2]!=null){
            process.setSumDate_actual_start((Date) objs[2]);
        }
        if(objs[3]!=null){
            process.setSumDate_actual_end((Date) objs[3]);
        }
        if(objs[4]!=null){
            process.setSumDate_plan_start((Date) objs[4]);
        }
        if(objs[5]!=null){
            process.setSumDate_plan_end((Date) objs[5]);
        }
        if(objs[6]!=null){
            process.setSumDate_predict_start((Date) objs[6]);
        }
        if(objs[7]!=null){
            process.setSumDate_predict_end((Date) objs[7]);
        }
        return process;
    }
}
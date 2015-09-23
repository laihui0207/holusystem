package com.huivip.holu.service.impl;

import com.huivip.holu.dao.SummaryTotalDao;
import com.huivip.holu.model.Setting;
import com.huivip.holu.model.SummaryItem;
import com.huivip.holu.model.SummaryTotal;
import com.huivip.holu.model.User;
import com.huivip.holu.service.CompanyDatabaseIndexManager;
import com.huivip.holu.service.SettingManager;
import com.huivip.holu.service.SummaryTotalManager;
import com.huivip.holu.service.UserManager;
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
        Date searchDate=getSumDate(sumDate);
        String searchDateString=simpleDateFormat.format(searchDate);
/*        searchDateString="2015-09-08";*/
        //a.processId,a.processName,b.SumWeight_actual,a.SumWeight_plan f
        List<Object[]> items=summaryTotalDao.getSummaryItem(searchDateString,processes,ItemStyle,startOrEnd,tableName );
        for(Object[] objs:items){
            SummaryItem item=convertSummaryItem(objs,ItemStyle);
            item.setSumDate(searchDate);
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
        Date searchDate=getSumDate(sumDate);
        String searchDateString=simpleDateFormat.format(searchDate);
/*        searchDateString="2015-09-08";*/
        List<String> validItem=summaryTotalDao.getSummaryValidItem(searchDateString, processes, ItemStyle, startOrEnd, tableName);
        if(null==validItem || validItem.size()==0) return result;
        for(String item:validItem){
            List<Object[]> itemSummary=summaryTotalDao.getSummaryDetailByItem(item, searchDateString, processes, ItemStyle, startOrEnd, tableName);
            List<SummaryItem> list=new ArrayList<>();
            for(Object[] objs:itemSummary){
             SummaryItem summaryItem=convertSummaryItem(objs,ItemStyle);
                summaryItem.setSumDate(searchDate);
                list.add(summaryItem);
            }
            result.put(item,list);
        }
        return result;
    }

    @Override
    public HashMap<String, List<SummaryItem>> getSummaryDetail(String userID, String itemID, String ItemStyle, String sumDate, String startOrEnd) {
        HashMap<String,List<SummaryItem>> result=new HashMap<>();
        User user=userManager.getUserByUserID(userID);
        String summaryTableName=companyDatabaseIndexManager.getTableNameByCompanyAndTableStyle(user.getCompany().getCompanyId(),"SummaryTable");
        String processes=getHomeProcessIds(userID);
        if(processes==null || processes.equalsIgnoreCase("")) return result;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date searchDate=getSumDate(sumDate);
        String searchDateString=simpleDateFormat.format(searchDate);
/*        searchDateString="2015-09-08";*/
        if(ItemStyle.equalsIgnoreCase("project")){
            List<String> validItem=summaryTotalDao.getDetailValidProjectItem(itemID,searchDateString,processes,startOrEnd,summaryTableName);
            if(null==validItem || validItem.size()==0) return result;
            for(String item:validItem){
                List<Object[]> itemDetail=summaryTotalDao.getDetailProjectDataByItem(itemID,item,searchDateString,processes,startOrEnd,summaryTableName);
                List<SummaryItem> list=new ArrayList<>();
                for(Object[] objs:itemDetail){
                    SummaryItem summaryItem=convertSummaryItem(objs,ItemStyle);
                    summaryItem.setSumDate(searchDate);
                    list.add(summaryItem);
                }
                result.put(item,list);
            }
        }
        else if(ItemStyle.equalsIgnoreCase("factory")){
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
        item.setProcessID((String) objs[0]);
        item.setItemName((String) objs[1]);
        item.setItemID((String) objs[2]);
        item.setItemStyle(style);
        if(objs[3]==null){
            item.setActual(0);
        }
        else {
            item.setActual((Double) objs[3]);
        }
        if(objs[4]==null){
            item.setPlan(0);
        }
        else {
            item.setPlan((Double) objs[4]);
        }
        return item;
    }
}
package com.huivip.holu.service.impl;

import com.huivip.holu.dao.SummaryDao;
import com.huivip.holu.dao.UserDao;
import com.huivip.holu.model.Setting;
import com.huivip.holu.model.Summary;
import com.huivip.holu.model.SummaryItem;
import com.huivip.holu.model.User;
import com.huivip.holu.service.CompanyDatabaseIndexManager;
import com.huivip.holu.service.SettingManager;
import com.huivip.holu.service.SummaryManager;
import com.huivip.holu.service.UserManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import net.sf.ehcache.search.aggregator.Sum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;
import javax.ws.rs.DefaultValue;

@Service("summaryManager")
@WebService(serviceName = "SummaryService", endpointInterface = "com.huivip.holu.service.SummaryManager")
public class SummaryManagerImpl extends GenericManagerImpl<Summary, Long> implements SummaryManager {
    SummaryDao summaryDao;
    @Autowired
    UserDao userDao;
    @Autowired
    CompanyDatabaseIndexManager companyDatabaseIndexManager;
    @Autowired
    UserManager userManager;
    @Autowired
    SettingManager settingManager;

    @Autowired
    public SummaryManagerImpl(SummaryDao summaryDao) {
        super(summaryDao);
        this.summaryDao = summaryDao;
    }

    @Override
    public List<Summary> summaryList(String userId) {
        User user=userDao.getUserByUserID(userId);
        String summaryTableName=companyDatabaseIndexManager.getTableNameByCompanyAndTableStyle(user.getCompany().getCompanyId(),"Summary");
        List<Summary> dataList=summaryDao.summaryList(summaryTableName);
        return dataList;
    }

    @Override
    public List<SummaryItem> getSummaryDetail(String userID, String itemID, String ItemStyle,  String sumDate,String startOrEnd) {
        User user=userDao.getUserByUserID(userID);
        String summaryTableName=companyDatabaseIndexManager.getTableNameByCompanyAndTableStyle(user.getCompany().getCompanyId(),"Summary");
        String processIds=getHomeProcessIds(userID);

        return null;
    }
    private String getHomeProcessIds(String userID){
        User user=userManager.getUserByUserID(userID);
        Setting setting=settingManager.getSettingBySearch(user.getCompany().getCompanyId(),"HomePage","HomePageProcessByTotal");
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

}
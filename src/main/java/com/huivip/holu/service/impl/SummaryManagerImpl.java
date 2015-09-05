package com.huivip.holu.service.impl;

import com.huivip.holu.dao.SummaryDao;
import com.huivip.holu.dao.UserDao;
import com.huivip.holu.model.Summary;
import com.huivip.holu.model.User;
import com.huivip.holu.service.CompanyDatabaseIndexManager;
import com.huivip.holu.service.SummaryManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import net.sf.ehcache.search.aggregator.Sum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("summaryManager")
@WebService(serviceName = "SummaryService", endpointInterface = "com.huivip.holu.service.SummaryManager")
public class SummaryManagerImpl extends GenericManagerImpl<Summary, Long> implements SummaryManager {
    SummaryDao summaryDao;
    @Autowired
    UserDao userDao;
    @Autowired
    CompanyDatabaseIndexManager companyDatabaseIndexManager;

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
}
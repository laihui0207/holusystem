package com.huivip.holu.service.impl;

import com.huivip.holu.dao.ProcessMidDao;
import com.huivip.holu.model.ProcessMid;
import com.huivip.holu.model.User;
import com.huivip.holu.service.CompanyDatabaseIndexManager;
import com.huivip.holu.service.ProcessMidManager;
import com.huivip.holu.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

@Service("processMidManager")
@WebService(serviceName = "ProcessMidService", endpointInterface = "com.huivip.holu.service.ProcessMidManager")
public class ProcessMidManagerImpl extends GenericManagerImpl<ProcessMid, Long> implements ProcessMidManager {
    ProcessMidDao processMidDao;
    @Autowired
    UserManager userManager;
    @Autowired
    CompanyDatabaseIndexManager companyDatabaseIndexManager;

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
}
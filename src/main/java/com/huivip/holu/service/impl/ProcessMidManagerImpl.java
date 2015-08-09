package com.huivip.holu.service.impl;

import com.huivip.holu.dao.ProcessMidDao;
import com.huivip.holu.model.ProcessMid;
import com.huivip.holu.model.User;
import com.huivip.holu.service.CompanyDatabaseIndexManager;
import com.huivip.holu.service.ProcessMidManager;
import com.huivip.holu.service.UserManager;
import com.huivip.holu.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import javax.ws.rs.FormParam;
import java.text.ParseException;
import java.util.Date;

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

    @Override
    public ProcessMid save(String subComponentID,String styleProcessID,String processNote,String startDate,String endDate,String positionGPS,String userID) {
        ProcessMid processMid = new ProcessMid();
        processMid.setCreateDate(new Date());
        processMid.setSubComponentID(subComponentID);
        processMid.setStyleProcessID(styleProcessID);
        processMid.setProcessNote(processNote);
        processMid.setPositionGPS(positionGPS);
      /*  processMid.setStartDate(new Date(Long.parseLong(startDate)));
        processMid.setEndDate(new Date(Long.parseLong(endDate)));*/
        processMid.setUser(userManager.getUserByUserID(userID));
        // to do  check if confirm again
        return save(processMid, userID);
    }
}
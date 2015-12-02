package com.huivip.holu.service.impl;

import com.huivip.holu.dao.ProcessMidDao;
import com.huivip.holu.model.ProcessMid;
import com.huivip.holu.model.User;
import com.huivip.holu.service.CompanyDatabaseIndexManager;
import com.huivip.holu.service.ProcessMidManager;
import com.huivip.holu.service.UserManager;
import com.huivip.holu.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import javax.ws.rs.FormParam;
import java.text.ParseException;
import java.util.Date;

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
    public ProcessMid save(String subComponentID,String styleProcessID,String processNote,
                           String startDate,String endDate,String positionGPS,String positionName,String userID) {
        ProcessMid processMid = new ProcessMid();
        processMid.setCreateDate(new Date());
        processMid.setSubComponentID(subComponentID);
        processMid.setStyleProcessID(styleProcessID);
        processMid.setProcessNote(processNote);
        processMid.setPositionGPS(positionGPS);
        processMid.setPositionName(positionName);
        processMid.setUser(userManager.getUserByUserID(userID));
        // to do  check if confirm again
        return save(processMid, userID);
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
        processMid.setUser(userManager.getUserByUserID(userID));
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
        processMid.setUser(userManager.getUserByUserID(userID));
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
        processMid.setUser(userManager.getUserByUserID(userID));
        log.info("User send question:"+subComponentID+","+styleProcessID+
                " : "+processMid.getProcessNote()+" at "+processMid.getPositionName());
        // to do  check if confirm again
        return save(processMid, userID);
    }
}
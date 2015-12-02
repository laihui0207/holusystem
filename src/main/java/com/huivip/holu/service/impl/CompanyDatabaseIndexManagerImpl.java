package com.huivip.holu.service.impl;

import com.huivip.holu.dao.CompanyDatabaseIndexDao;
import com.huivip.holu.model.CompanyDatabaseIndex;
import com.huivip.holu.service.CompanyDatabaseIndexManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("companyDatabaseIndexManager")
@WebService(serviceName = "CompanyDatabaseIndexService", endpointInterface = "com.huivip.holu.service.CompanyDatabaseIndexManager")
public class CompanyDatabaseIndexManagerImpl extends GenericManagerImpl<CompanyDatabaseIndex, Long> implements CompanyDatabaseIndexManager {
    CompanyDatabaseIndexDao companyDatabaseIndexDao;

    @Autowired
    public CompanyDatabaseIndexManagerImpl(CompanyDatabaseIndexDao companyDatabaseIndexDao) {
        super(companyDatabaseIndexDao);
        this.companyDatabaseIndexDao = companyDatabaseIndexDao;
    }

    @Override
    public String getTableNameByCompanyAndTableStyle(String companyId, String tableStyle) {
        String cacheKey=tableStyle+"_"+companyId;
        String tableName=tableCache.peek(cacheKey);
        if(tableName==null){
            tableName=companyDatabaseIndexDao.getTableNameByCompanyAndTableStyle(companyId,tableStyle);;
            tableCache.put(cacheKey,tableName);
        }
        return tableName;
    }

    @Override
    public String getComponentTableNameByCompany(String companyID) {
        String cacheKey="ComponentList_"+companyID;
        String tableName=tableCache.peek(cacheKey);
        if(tableName==null){
            tableName=companyDatabaseIndexDao.getTableNameByCompanyAndTableStyle(companyID,"ComponentList");
            tableCache.put(cacheKey,tableName);
        }
        return tableName;
    }

    @Override
    public String getSubComponentTableNameByCompany(String companyID) {
        String cacheKey="SubComponentList_"+companyID;
        String tableName=tableCache.peek(cacheKey);
        if(tableName==null){
            tableName=companyDatabaseIndexDao.getTableNameByCompanyAndTableStyle(companyID,"SubComponentList");
            tableCache.put(cacheKey,tableName);
        }
        return tableName;
    }

    @Override
    public String getProcessMidTableNameByCompany(String companyID) {
        String cacheKey="ProcessMidTable_"+companyID;
        String tableName=tableCache.peek(cacheKey);
        if(tableName==null){
            tableName=companyDatabaseIndexDao.getTableNameByCompanyAndTableStyle(companyID,"ProcessMidTable");
            tableCache.put(cacheKey,tableName);
        }
        return tableName;
    }
}
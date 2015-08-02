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
        return companyDatabaseIndexDao.getTableNameByCompanyAndTableStyle(companyId,tableStyle);
    }

    @Override
    public String getComponentTableNameByCompany(String companyID) {
        return companyDatabaseIndexDao.getTableNameByCompanyAndTableStyle(companyID,"ComponentList");
    }

    @Override
    public String getSubComponentTableNameByCompany(String companyID) {
        return companyDatabaseIndexDao.getTableNameByCompanyAndTableStyle(companyID,"SubComponentList");
    }

    @Override
    public String getProcessMidTableNameByCompany(String companyID) {
        return companyDatabaseIndexDao.getTableNameByCompanyAndTableStyle(companyID,"ProcessMidTable");
    }
}
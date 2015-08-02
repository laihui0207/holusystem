package com.huivip.holu.service;

import com.huivip.holu.model.CompanyDatabaseIndex;

import javax.jws.WebService;

@WebService
public interface CompanyDatabaseIndexManager extends GenericManager<CompanyDatabaseIndex, Long> {

    String getTableNameByCompanyAndTableStyle(String companyId,String tableStyle);
    String getComponentTableNameByCompany(String companyID);
    String getSubComponentTableNameByCompany(String companyID);
    String getProcessMidTableNameByCompany(String companyID);
}
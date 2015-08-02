package com.huivip.holu.dao;

import com.huivip.holu.model.CompanyDatabaseIndex;

/**
 * An interface that provides a data management interface to the CompanyDatabaseIndex table.
 */
public interface CompanyDatabaseIndexDao extends GenericDao<CompanyDatabaseIndex, Long> {
    String getTableNameByCompanyAndTableStyle(String companyId,String tableStyle);
}
package com.huivip.holu.dao;

import com.huivip.holu.model.Company;

/**
 * An interface that provides a data management interface to the Company table.
 */
public interface CompanyDao extends GenericDao<Company, Long> {

    Company getCompanyByCompanyID(String companyID);

}
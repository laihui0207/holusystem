package com.huivip.holu.service;

import com.huivip.holu.model.Company;

import javax.jws.WebService;

@WebService
public interface CompanyManager extends GenericManager<Company, Long> {

    Company getCompanyByCompanyID(String companyID);
    
}
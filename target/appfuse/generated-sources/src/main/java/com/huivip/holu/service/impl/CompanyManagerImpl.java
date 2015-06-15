package com.huivip.holu.service.impl;

import com.huivip.holu.dao.CompanyDao;
import com.huivip.holu.model.Company;
import com.huivip.holu.service.CompanyManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("companyManager")
@WebService(serviceName = "CompanyService", endpointInterface = "com.huivip.holu.service.CompanyManager")
public class CompanyManagerImpl extends GenericManagerImpl<Company, Long> implements CompanyManager {
    CompanyDao companyDao;

    @Autowired
    public CompanyManagerImpl(CompanyDao companyDao) {
        super(companyDao);
        this.companyDao = companyDao;
    }
}
package com.huivip.holu.dao;

import com.huivip.holu.dao.BaseDaoTestCase;
import com.huivip.holu.model.Company;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CompanyDaoTest extends BaseDaoTestCase {
    @Autowired
    private CompanyDao companyDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveCompany() {
        Company company = new Company();

        // enter all required fields

        log.debug("adding company...");
        company = companyDao.save(company);

        company = companyDao.get(company.getId());

        assertNotNull(company.getId());

        log.debug("removing company...");

        companyDao.remove(company.getId());

        // should throw DataAccessException 
        companyDao.get(company.getId());
    }
}
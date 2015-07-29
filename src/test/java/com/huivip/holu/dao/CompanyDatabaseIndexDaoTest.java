package com.huivip.holu.dao;

import com.huivip.holu.dao.BaseDaoTestCase;
import com.huivip.holu.model.CompanyDatabaseIndex;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CompanyDatabaseIndexDaoTest extends BaseDaoTestCase {
    @Autowired
    private CompanyDatabaseIndexDao companyDatabaseIndexDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveCompanyDatabaseIndex() {
        CompanyDatabaseIndex companyDatabaseIndex = new CompanyDatabaseIndex();

        // enter all required fields

        log.debug("adding companyDatabaseIndex...");
        companyDatabaseIndex = companyDatabaseIndexDao.save(companyDatabaseIndex);

        companyDatabaseIndex = companyDatabaseIndexDao.get(companyDatabaseIndex.getId());

        assertNotNull(companyDatabaseIndex.getId());

        log.debug("removing companyDatabaseIndex...");

        companyDatabaseIndexDao.remove(companyDatabaseIndex.getId());

        // should throw DataAccessException 
        companyDatabaseIndexDao.get(companyDatabaseIndex.getId());
    }
}
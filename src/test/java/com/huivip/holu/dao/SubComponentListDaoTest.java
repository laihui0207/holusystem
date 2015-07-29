package com.huivip.holu.dao;

import com.huivip.holu.dao.BaseDaoTestCase;
import com.huivip.holu.model.SubComponentList;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SubComponentListDaoTest extends BaseDaoTestCase {
    @Autowired
    private SubComponentListDao subComponentListDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveSubComponentList() {
        SubComponentList subComponentList = new SubComponentList();

        // enter all required fields

        log.debug("adding subComponentList...");
        subComponentList = subComponentListDao.save(subComponentList);

        subComponentList = subComponentListDao.get(subComponentList.getId());

        assertNotNull(subComponentList.getId());

        log.debug("removing subComponentList...");

        subComponentListDao.remove(subComponentList.getId());

        // should throw DataAccessException 
        subComponentListDao.get(subComponentList.getId());
    }
}
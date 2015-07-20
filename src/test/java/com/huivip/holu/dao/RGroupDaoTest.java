package com.huivip.holu.dao;

import com.huivip.holu.dao.BaseDaoTestCase;
import com.huivip.holu.model.RGroup;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RGroupDaoTest extends BaseDaoTestCase {
    @Autowired
    private RGroupDao rGroupDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveRGroup() {
        RGroup rGroup = new RGroup();

        // enter all required fields

        log.debug("adding rGroup...");
        rGroup = rGroupDao.save(rGroup);

        rGroup = rGroupDao.get(rGroup.getId());

        assertNotNull(rGroup.getId());

        log.debug("removing rGroup...");

        rGroupDao.remove(rGroup.getId());

        // should throw DataAccessException 
        rGroupDao.get(rGroup.getId());
    }
}
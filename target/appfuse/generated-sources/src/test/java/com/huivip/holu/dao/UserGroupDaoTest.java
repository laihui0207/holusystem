package com.huivip.holu.dao;

import com.huivip.holu.dao.BaseDaoTestCase;
import com.huivip.holu.model.UserGroup;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserGroupDaoTest extends BaseDaoTestCase {
    @Autowired
    private UserGroupDao userGroupDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveUserGroup() {
        UserGroup userGroup = new UserGroup();

        // enter all required fields

        log.debug("adding userGroup...");
        userGroup = userGroupDao.save(userGroup);

        userGroup = userGroupDao.get(userGroup.getId());

        assertNotNull(userGroup.getId());

        log.debug("removing userGroup...");

        userGroupDao.remove(userGroup.getId());

        // should throw DataAccessException 
        userGroupDao.get(userGroup.getId());
    }
}
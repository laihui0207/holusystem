package com.huivip.holu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.huivip.holu.dao.UserGroupDao;
import com.huivip.holu.model.UserGroup;
import com.huivip.holu.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class UserGroupManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private UserGroupManagerImpl manager;

    @Mock
    private UserGroupDao dao;

    @Test
    public void testGetUserGroup() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final UserGroup userGroup = new UserGroup();
        given(dao.get(id)).willReturn(userGroup);

        //when
        UserGroup result = manager.get(id);

        //then
        assertSame(userGroup, result);
    }

    @Test
    public void testGetUserGroups() {
        log.debug("testing getAll...");
        //given
        final List<UserGroup> userGroups = new ArrayList<>();
        given(dao.getAll()).willReturn(userGroups);

        //when
        List result = manager.getAll();

        //then
        assertSame(userGroups, result);
    }

    @Test
    public void testSaveUserGroup() {
        log.debug("testing save...");

        //given
        final UserGroup userGroup = new UserGroup();
        // enter all required fields

        given(dao.save(userGroup)).willReturn(userGroup);

        //when
        manager.save(userGroup);

        //then
        verify(dao).save(userGroup);
    }

    @Test
    public void testRemoveUserGroup() {
        log.debug("testing remove...");

        //given
        final Long id = -11L;
        willDoNothing().given(dao).remove(id);

        //when
        manager.remove(id);

        //then
        verify(dao).remove(id);
    }
}

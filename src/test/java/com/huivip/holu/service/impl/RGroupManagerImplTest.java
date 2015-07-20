package com.huivip.holu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.huivip.holu.dao.RGroupDao;
import com.huivip.holu.model.RGroup;
import com.huivip.holu.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class RGroupManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private RGroupManagerImpl manager;

    @Mock
    private RGroupDao dao;

    @Test
    public void testGetRGroup() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final RGroup rGroup = new RGroup();
        given(dao.get(id)).willReturn(rGroup);

        //when
        RGroup result = manager.get(id);

        //then
        assertSame(rGroup, result);
    }

    @Test
    public void testGetRGroups() {
        log.debug("testing getAll...");
        //given
        final List<RGroup> rGroups = new ArrayList<>();
        given(dao.getAll()).willReturn(rGroups);

        //when
        List result = manager.getAll();

        //then
        assertSame(rGroups, result);
    }

    @Test
    public void testSaveRGroup() {
        log.debug("testing save...");

        //given
        final RGroup rGroup = new RGroup();
        // enter all required fields

        given(dao.save(rGroup)).willReturn(rGroup);

        //when
        manager.save(rGroup);

        //then
        verify(dao).save(rGroup);
    }

    @Test
    public void testRemoveRGroup() {
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

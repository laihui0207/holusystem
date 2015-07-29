package com.huivip.holu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.huivip.holu.dao.SubComponentListDao;
import com.huivip.holu.model.SubComponentList;
import com.huivip.holu.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class SubComponentListManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private SubComponentListManagerImpl manager;

    @Mock
    private SubComponentListDao dao;

    @Test
    public void testGetSubComponentList() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final SubComponentList subComponentList = new SubComponentList();
        given(dao.get(id)).willReturn(subComponentList);

        //when
        SubComponentList result = manager.get(id);

        //then
        assertSame(subComponentList, result);
    }

    @Test
    public void testGetSubComponentLists() {
        log.debug("testing getAll...");
        //given
        final List<SubComponentList> subComponentLists = new ArrayList<>();
        given(dao.getAll()).willReturn(subComponentLists);

        //when
        List result = manager.getAll();

        //then
        assertSame(subComponentLists, result);
    }

    @Test
    public void testSaveSubComponentList() {
        log.debug("testing save...");

        //given
        final SubComponentList subComponentList = new SubComponentList();
        // enter all required fields

        given(dao.save(subComponentList)).willReturn(subComponentList);

        //when
        manager.save(subComponentList);

        //then
        verify(dao).save(subComponentList);
    }

    @Test
    public void testRemoveSubComponentList() {
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

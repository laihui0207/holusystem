package com.huivip.holu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.huivip.holu.dao.ComponentStyleDao;
import com.huivip.holu.model.ComponentStyle;
import com.huivip.holu.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class ComponentStyleManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private ComponentStyleManagerImpl manager;

    @Mock
    private ComponentStyleDao dao;

    @Test
    public void testGetComponentStyle() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final ComponentStyle componentStyle = new ComponentStyle();
        given(dao.get(id)).willReturn(componentStyle);

        //when
        ComponentStyle result = manager.get(id);

        //then
        assertSame(componentStyle, result);
    }

    @Test
    public void testGetComponentStyles() {
        log.debug("testing getAll...");
        //given
        final List<ComponentStyle> componentStyles = new ArrayList<>();
        given(dao.getAll()).willReturn(componentStyles);

        //when
        List result = manager.getAll();

        //then
        assertSame(componentStyles, result);
    }

    @Test
    public void testSaveComponentStyle() {
        log.debug("testing save...");

        //given
        final ComponentStyle componentStyle = new ComponentStyle();
        // enter all required fields
        componentStyle.setProcessOrder(1762570934);

        given(dao.save(componentStyle)).willReturn(componentStyle);

        //when
        manager.save(componentStyle);

        //then
        verify(dao).save(componentStyle);
    }

    @Test
    public void testRemoveComponentStyle() {
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

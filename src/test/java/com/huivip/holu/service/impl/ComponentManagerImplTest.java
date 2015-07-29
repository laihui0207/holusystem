package com.huivip.holu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.huivip.holu.dao.ComponentDao;
import com.huivip.holu.model.Component;
import com.huivip.holu.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class ComponentManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private ComponentManagerImpl manager;

    @Mock
    private ComponentDao dao;

    @Test
    public void testGetComponent() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final Component component = new Component();
        given(dao.get(id)).willReturn(component);

        //when
        Component result = manager.get(id);

        //then
        assertSame(component, result);
    }

    @Test
    public void testGetComponents() {
        log.debug("testing getAll...");
        //given
        final List<Component> components = new ArrayList<>();
        given(dao.getAll()).willReturn(components);

        //when
        List result = manager.getAll();

        //then
        assertSame(components, result);
    }

    @Test
    public void testSaveComponent() {
        log.debug("testing save...");

        //given
        final Component component = new Component();
        // enter all required fields
        component.setLength(new Float(3.2717387E38));
        component.setPrice(new Float(1.2140769E38));
        component.setSize(new Float(1.419379E38));
        component.setWeight(new Float(2.6007661E38));

        given(dao.save(component)).willReturn(component);

        //when
        manager.save(component);

        //then
        verify(dao).save(component);
    }

    @Test
    public void testRemoveComponent() {
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

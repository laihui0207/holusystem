package com.huivip.holu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.huivip.holu.dao.ProjectIndexDao;
import com.huivip.holu.model.ProjectIndex;
import com.huivip.holu.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class ProjectIndexManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private ProjectIndexManagerImpl manager;

    @Mock
    private ProjectIndexDao dao;

    @Test
    public void testGetProjectIndex() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final ProjectIndex projectIndex = new ProjectIndex();
        given(dao.get(id)).willReturn(projectIndex);

        //when
        ProjectIndex result = manager.get(id);

        //then
        assertSame(projectIndex, result);
    }

    @Test
    public void testGetProjectIndices() {
        log.debug("testing getAll...");
        //given
        final List<ProjectIndex> projectIndices = new ArrayList<>();
        given(dao.getAll()).willReturn(projectIndices);

        //when
        List result = manager.getAll();

        //then
        assertSame(projectIndices, result);
    }

    @Test
    public void testSaveProjectIndex() {
        log.debug("testing save...");

        //given
        final ProjectIndex projectIndex = new ProjectIndex();
        // enter all required fields

        given(dao.save(projectIndex)).willReturn(projectIndex);

        //when
        manager.save(projectIndex);

        //then
        verify(dao).save(projectIndex);
    }

    @Test
    public void testRemoveProjectIndex() {
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

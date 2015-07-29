package com.huivip.holu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.huivip.holu.dao.ProjectDao;
import com.huivip.holu.model.Project;
import com.huivip.holu.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class ProjectManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private ProjectManagerImpl manager;

    @Mock
    private ProjectDao dao;

    @Test
    public void testGetProject() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final Project project = new Project();
        given(dao.get(id)).willReturn(project);

        //when
        Project result = manager.get(id);

        //then
        assertSame(project, result);
    }

    @Test
    public void testGetProjects() {
        log.debug("testing getAll...");
        //given
        final List<Project> projects = new ArrayList<>();
        given(dao.getAll()).willReturn(projects);

        //when
        List result = manager.getAll();

        //then
        assertSame(projects, result);
    }

    @Test
    public void testSaveProject() {
        log.debug("testing save...");

        //given
        final Project project = new Project();
        // enter all required fields

        given(dao.save(project)).willReturn(project);

        //when
        manager.save(project);

        //then
        verify(dao).save(project);
    }

    @Test
    public void testRemoveProject() {
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

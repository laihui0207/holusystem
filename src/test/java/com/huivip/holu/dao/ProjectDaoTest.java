package com.huivip.holu.dao;

import com.huivip.holu.dao.BaseDaoTestCase;
import com.huivip.holu.model.Project;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProjectDaoTest extends BaseDaoTestCase {
    @Autowired
    private ProjectDao projectDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveProject() {
        Project project = new Project();

        // enter all required fields

        log.debug("adding project...");
        project = projectDao.save(project);

        project = projectDao.get(project.getId());

        assertNotNull(project.getId());

        log.debug("removing project...");

        projectDao.remove(project.getId());

        // should throw DataAccessException 
        projectDao.get(project.getId());
    }
}
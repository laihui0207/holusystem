package com.huivip.holu.dao;

import com.huivip.holu.dao.BaseDaoTestCase;
import com.huivip.holu.model.ProjectIndex;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProjectIndexDaoTest extends BaseDaoTestCase {
    @Autowired
    private ProjectIndexDao projectIndexDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveProjectIndex() {
        ProjectIndex projectIndex = new ProjectIndex();

        // enter all required fields

        log.debug("adding projectIndex...");
        projectIndex = projectIndexDao.save(projectIndex);

        projectIndex = projectIndexDao.get(projectIndex.getId());

        assertNotNull(projectIndex.getId());

        log.debug("removing projectIndex...");

        projectIndexDao.remove(projectIndex.getId());

        // should throw DataAccessException 
        projectIndexDao.get(projectIndex.getId());
    }
}
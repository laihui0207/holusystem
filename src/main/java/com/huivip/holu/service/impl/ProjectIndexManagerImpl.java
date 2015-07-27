package com.huivip.holu.service.impl;

import com.huivip.holu.dao.ProjectIndexDao;
import com.huivip.holu.model.ProjectIndex;
import com.huivip.holu.service.ProjectIndexManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("projectIndexManager")
@WebService(serviceName = "ProjectIndexService", endpointInterface = "com.huivip.holu.service.ProjectIndexManager")
public class ProjectIndexManagerImpl extends GenericManagerImpl<ProjectIndex, Long> implements ProjectIndexManager {
    ProjectIndexDao projectIndexDao;

    @Autowired
    public ProjectIndexManagerImpl(ProjectIndexDao projectIndexDao) {
        super(projectIndexDao);
        this.projectIndexDao = projectIndexDao;
    }

    @Override
    public ProjectIndex getProjectIndexByProject(String projectID) {
        return projectIndexDao.getProjectIndexByProject(projectID);
    }
}
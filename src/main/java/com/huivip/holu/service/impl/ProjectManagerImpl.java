package com.huivip.holu.service.impl;

import com.huivip.holu.dao.ProjectDao;
import com.huivip.holu.model.Project;
import com.huivip.holu.service.ProjectManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("projectManager")
@WebService(serviceName = "ProjectService", endpointInterface = "com.huivip.holu.service.ProjectManager")
public class ProjectManagerImpl extends GenericManagerImpl<Project, Long> implements ProjectManager {
    ProjectDao projectDao;

    @Autowired
    public ProjectManagerImpl(ProjectDao projectDao) {
        super(projectDao);
        this.projectDao = projectDao;
    }
}
package com.huivip.holu.service.impl;

import com.huivip.holu.dao.ProjectDao;
import com.huivip.holu.dao.UserDao;
import com.huivip.holu.model.Project;
import com.huivip.holu.model.User;
import com.huivip.holu.service.ProjectManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;

@Service("projectManager")
@WebService(serviceName = "ProjectService", endpointInterface = "com.huivip.holu.service.ProjectManager")
public class ProjectManagerImpl extends GenericManagerImpl<Project, Long> implements ProjectManager {
    ProjectDao projectDao;
    @Autowired
    UserDao userDao;

    @Autowired
    public ProjectManagerImpl(ProjectDao projectDao) {
        super(projectDao);
        this.projectDao = projectDao;
    }

    @Override
    public List<Project> getProjectListByCompany(String CompanyID) {
        return projectDao.getProjectListByCompany(CompanyID);
    }

    @Override
    public List<Project> getProjectListByUser(String userId) {
        User user=userDao.get(Long.parseLong(userId));
        return projectDao.getProjectListByCompany(user.getCompany().getId().toString());
    }
}
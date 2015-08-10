package com.huivip.holu.service.impl;

import com.huivip.holu.dao.ProjectDao;
import com.huivip.holu.model.Project;
import com.huivip.holu.service.ProjectManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import com.huivip.holu.webapp.helper.PaginatedListImpl;
import org.displaytag.properties.SortOrderEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;
import javax.ws.rs.DefaultValue;

@Service("projectManager")
@WebService(serviceName = "ProjectService", endpointInterface = "com.huivip.holu.service.ProjectManager")
public class ProjectManagerImpl extends GenericManagerImpl<Project, Long> implements ProjectManager {
    ProjectDao projectDao;

    @Autowired
    public ProjectManagerImpl(ProjectDao projectDao) {
        super(projectDao);
        this.projectDao = projectDao;
    }

    /*@Override
    public List<Project> getMyProject(String userID) {
        return getProjectByUserID(userID,null,null);
    }*/

    @Override
    public List<Project> getMyProject(String userID,String parentID,String page, String pageSize) {
        ExtendedPaginatedList list =new PaginatedListImpl();
        list.setPageSize(Integer.parseInt(pageSize));
        list.setIndex(Integer.parseInt(page));
        list.setSortCriterion("startDate");
        list.setSortDirection(SortOrderEnum.DESCENDING);
        List<Project> dataList=getProjectByUserID(userID,parentID,list);
        return list.getList();
    }

  /*  @Override
    public List<Project> getMySubProject(String userID, String parentID) {
        return getProjectByUserID(userID,parentID,null);
    }*/

    @Override
    public List<Project> getProjectByUserID(String userID, String parentProject,ExtendedPaginatedList list) {
        return projectDao.getProjectByUserID(userID,parentProject,list);
    }

    @Override
    public Project getProjectByprojectID(String projectID) {
        return projectDao.getProjectByprojectID(projectID);
    }
}
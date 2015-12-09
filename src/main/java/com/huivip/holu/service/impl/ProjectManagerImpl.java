package com.huivip.holu.service.impl;

import com.huivip.holu.dao.ProjectDao;
import com.huivip.holu.dao.UserDao;
import com.huivip.holu.model.Project;
import com.huivip.holu.service.ProjectManager;

import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import com.huivip.holu.webapp.helper.PaginatedListImpl;
import org.displaytag.properties.SortOrderEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.jws.WebService;

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
    private Set<Project> collectMyProjects(Set<Project> list){
        Set<Project> result=new HashSet<>();
        if(list==null || list.size()==0 ) return result;
        for(Project p: list){
            if(p.getChildProjects()==null || p.getChildProjects().size()==0){
                result.add(p);
            }
            else {
                result.addAll(collectMyProjects(p.getChildProjects()));
            }
        }
        return result;
    }
    @Override
    public List<Project> getMyAllProject(String userId) {
        List<Project> projectList=projectDao.getProjectByUserID(userId,"",null);
        List<Project> myProject=new ArrayList<>();
        for(Project project:projectList){
            if(project.getChildProjects()==null || project.getChildProjects().size()==0){
                myProject.add(project);
            }
            else {
                myProject.addAll(collectMyProjects(project.getChildProjects()));
            }
        }
        return myProject;
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

    @Override
    public List<String> getProjectIDByUserID(String userID) {
        return projectDao.getProjectIDsByUserID(userID);
    }
}
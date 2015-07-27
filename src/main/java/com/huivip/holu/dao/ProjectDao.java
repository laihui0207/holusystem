package com.huivip.holu.dao;

import com.huivip.holu.model.Project;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;

import java.util.List;

/**
 * An interface that provides a data management interface to the Project table.
 */
public interface ProjectDao extends GenericDao<Project, Long> {

    List<Project> getProjectByUserID(String userID,String parentProject,ExtendedPaginatedList list);
    Project getProjectByprojectID(String projectID);
}
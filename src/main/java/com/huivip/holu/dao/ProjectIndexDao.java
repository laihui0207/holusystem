package com.huivip.holu.dao;

import com.huivip.holu.model.ProjectIndex;

/**
 * An interface that provides a data management interface to the ProjectIndex table.
 */
public interface ProjectIndexDao extends GenericDao<ProjectIndex, Long> {
    ProjectIndex getProjectIndexByProject(String projectID);

}
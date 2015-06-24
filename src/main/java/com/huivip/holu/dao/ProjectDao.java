package com.huivip.holu.dao;

import com.huivip.holu.model.Project;

import java.util.List;

/**
 * An interface that provides a data management interface to the Project table.
 */
public interface ProjectDao extends GenericDao<Project, Long> {
    List<Project> getProjectListByCompany(String CompanyID);

}
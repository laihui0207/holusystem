package com.huivip.holu.service;

import com.huivip.holu.model.Project;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface ProjectManager extends GenericManager<Project, Long> {

    List<Project> getProjectByUserID(String userID,String parentProject,ExtendedPaginatedList list);
    Project getProjectByprojectID(String projectID);

    
}
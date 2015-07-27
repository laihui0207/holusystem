package com.huivip.holu.service;

import com.huivip.holu.model.ProjectIndex;

import javax.jws.WebService;

@WebService
public interface ProjectIndexManager extends GenericManager<ProjectIndex, Long> {
    ProjectIndex getProjectIndexByProject(String projectID);
    
}
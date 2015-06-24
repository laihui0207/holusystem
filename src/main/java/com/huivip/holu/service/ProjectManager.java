package com.huivip.holu.service;

import com.huivip.holu.model.Project;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface ProjectManager extends GenericManager<Project, Long> {

    List<Project> getProjectListByCompany(String CompanyID);
    
}
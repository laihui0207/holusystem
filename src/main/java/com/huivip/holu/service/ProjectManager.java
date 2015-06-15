package com.huivip.holu.service;

import com.huivip.holu.service.GenericManager;
import com.huivip.holu.model.Project;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface ProjectManager extends GenericManager<Project, Long> {
    
}
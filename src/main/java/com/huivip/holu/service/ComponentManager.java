package com.huivip.holu.service;

import com.huivip.holu.model.Component;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface ComponentManager extends GenericManager<Component, Long> {

    public List<Component> listComponentByProject(String projectID);
    
}
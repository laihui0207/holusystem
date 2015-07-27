package com.huivip.holu.service.impl;

import com.huivip.holu.dao.ComponentDao;
import com.huivip.holu.dao.ProjectIndexDao;
import com.huivip.holu.model.Component;
import com.huivip.holu.model.ProjectIndex;
import com.huivip.holu.service.ComponentManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("componentManager")
@WebService(serviceName = "ComponentService", endpointInterface = "com.huivip.holu.service.ComponentManager")
public class ComponentManagerImpl extends GenericManagerImpl<Component, Long> implements ComponentManager {
    ComponentDao componentDao;
    @Autowired
    ProjectIndexDao projectIndexDao;

    @Autowired
    public ComponentManagerImpl(ComponentDao componentDao) {
        super(componentDao);
        this.componentDao = componentDao;
    }

    @Override
    public List<Component> listComponentByProject(String projectID) {
        String tableName="U_SYSTEM_ComponentList";
        ProjectIndex projectIndex=projectIndexDao.getProjectIndexByProject(projectID);
        if(null!=projectIndex){
            tableName=projectIndex.getPartListTableName();
        }
        return componentDao.listComponentByProject(projectID,tableName);
    }
}
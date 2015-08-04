package com.huivip.holu.service.impl;

import com.huivip.holu.dao.CompanyDatabaseIndexDao;
import com.huivip.holu.dao.ComponentDao;
import com.huivip.holu.dao.UserDao;
import com.huivip.holu.model.Component;
import com.huivip.holu.model.User;
import com.huivip.holu.service.ComponentManager;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;

@Service("componentManager")
@WebService(serviceName = "ComponentService", endpointInterface = "com.huivip.holu.service.ComponentManager")
public class ComponentManagerImpl extends GenericManagerImpl<Component, Long> implements ComponentManager {
    ComponentDao componentDao;
    @Autowired
    CompanyDatabaseIndexDao companyDatabaseIndexDao;
    @Autowired
    UserDao userDao;

    @Autowired
    public ComponentManagerImpl(ComponentDao componentDao) {
        super(componentDao);
        this.componentDao = componentDao;
    }

    @Override
    public List<Component> listComponentByProject(String projectID, String userId) {
        return listComponentByProject(projectID,userId,null);
    }

    @Override
    public List<Component> listComponentByProject(String projectID,String userID,ExtendedPaginatedList list) {
        User user=userDao.getUserByUserID(userID);
        String tableName=companyDatabaseIndexDao.getTableNameByCompanyAndTableStyle(user.getCompany().getCompanyId(),"ComponentList");
        return componentDao.listComponentByProject(projectID,tableName,list);
    }

    @Override
    public Component getComponentByComponentID(String componentID,String userID) {
        User user=userDao.getUserByUserID(userID);
        String tableName=companyDatabaseIndexDao.getTableNameByCompanyAndTableStyle(user.getCompany().getCompanyId(),"ComponentList");
        return componentDao.getComponentByComponentID(componentID,tableName);
    }
}
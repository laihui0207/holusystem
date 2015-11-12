package com.huivip.holu.service.impl;

import com.huivip.holu.dao.CompanyDatabaseIndexDao;
import com.huivip.holu.dao.ComponentDao;
import com.huivip.holu.dao.SubComponentListDao;
import com.huivip.holu.dao.UserDao;
import com.huivip.holu.model.Component;
import com.huivip.holu.model.User;
import com.huivip.holu.service.ComponentManager;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import com.huivip.holu.webapp.helper.PaginatedListImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.HashSet;
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
    SubComponentListDao subComponentListDao;

    @Autowired
    public ComponentManagerImpl(ComponentDao componentDao) {
        super(componentDao);
        this.componentDao = componentDao;
    }

    @Override
    public List<Component> listComponentByProject(String projectID, String userId,String page, String pageSize) {
        ExtendedPaginatedList list =new PaginatedListImpl();
        list.setPageSize(Integer.parseInt(pageSize));
        list.setIndex(Integer.parseInt(page));
        List<Component> dataList=listComponentByProject(projectID,userId,list);
        return list.getList();
    }

    @Override
    public List<Component> listComponentByProject(String projectID,String userID,ExtendedPaginatedList list) {
        User user=userDao.getUserByUserID(userID);
        String tableName=companyDatabaseIndexDao.getTableNameByCompanyAndTableStyle(user.getCompany().getCompanyId(),"ComponentList");
        String subtableName=companyDatabaseIndexDao.getTableNameByCompanyAndTableStyle(user.getCompany().getCompanyId(),"SubComponentList");
        List<Component> components=componentDao.listComponentByProject(projectID,tableName,list);
        for(Component component:components){
            component.setSubComponentListSet(new HashSet(subComponentListDao.getSubComponentListByComponentID(component.getComponentID(), subtableName, null)));
        }
        if(list!=null){
            list.setList(components);
        }
        return components;
    }

    @Override
    public Component getComponentByComponentID(String componentID,String userID) {
        User user=userDao.getUserByUserID(userID);
        String tableName=companyDatabaseIndexDao.getTableNameByCompanyAndTableStyle(user.getCompany().getCompanyId(),"ComponentList");
        Component component=componentDao.getComponentByComponentID(componentID,tableName);
        if(component!=null){
            component.setSubComponentListSet(null);
        }
        return component;
    }
}
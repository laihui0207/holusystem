package com.huivip.holu.service.impl;

import com.huivip.holu.dao.CompanyDatabaseIndexDao;
import com.huivip.holu.dao.SubComponentListDao;
import com.huivip.holu.dao.UserDao;
import com.huivip.holu.model.*;
import com.huivip.holu.service.ComponentManager;
import com.huivip.holu.service.ProjectManager;
import com.huivip.holu.service.SubComponentListManager;
import com.huivip.holu.service.UserManager;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import com.huivip.holu.webapp.helper.PaginatedListImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@Service("subComponentListManager")
@WebService(serviceName = "SubComponentListService", endpointInterface = "com.huivip.holu.service.SubComponentListManager")
public class SubComponentListManagerImpl extends GenericManagerImpl<SubComponentList, Long> implements SubComponentListManager {
    SubComponentListDao subComponentListDao;
    @Autowired
    CompanyDatabaseIndexDao companyDatabaseIndexDao;
    @Autowired
    UserDao userDao;
    @Autowired
    UserManager userManager;
    @Autowired
    ComponentManager componentManager;
    @Autowired
    ProjectManager projectManager;



    @Autowired
    public SubComponentListManagerImpl(SubComponentListDao subComponentListDao) {
        super(subComponentListDao);
        this.subComponentListDao = subComponentListDao;
    }

    @Override
    public List<SubComponentList> getSubComponentListByComponentID(String componentID, String userID,ExtendedPaginatedList list) {
        User user=userDao.getUserByUserID(userID);
        String tableName=companyDatabaseIndexDao.getTableNameByCompanyAndTableStyle(user.getCompany().getCompanyId(),"SubComponentList");
        return subComponentListDao.getSubComponentListByComponentID(componentID,tableName,list);
    }

    @Override
    public Component getParentComponent(String subComponentId, String userId) {
        User user=userDao.getUserByUserID(userId);
        String tableName=companyDatabaseIndexDao.getTableNameByCompanyAndTableStyle(user.getCompany().getCompanyId(),"SubComponentList");
        String parentComponentId=subComponentListDao.getParentComponentId(subComponentId,tableName);
        Component component=componentManager.getComponentByComponentID(parentComponentId,userId);
        component.setSubComponentListSet(null);
        return component;
    }

    @Override
    public List<SubComponentList> getSubComponentListByComponentID(String componentID, String userID,String page,String pageSize) {
        ExtendedPaginatedList list =new PaginatedListImpl();
        list.setPageSize(Integer.parseInt(pageSize));
        list.setIndex(Integer.parseInt(page));
        return getSubComponentListByComponentID(componentID,userID,list);
    }

    @Override
    public SubComponentList getSubComponentBySubComponentID(String subComponentID, String userID) {
        User user=userDao.getUserByUserID(userID);
        String tableName=companyDatabaseIndexDao.getTableNameByCompanyAndTableStyle(user.getCompany().getCompanyId(),"SubComponentList");
        //Component parent=getParentComponent(subComponentID,userID);
        SubComponentList subComponentList=subComponentListDao.getSubComponentBySubComponentID(subComponentID,tableName);
        //subComponentList.setParentComponent(parent);
        return subComponentList;
    }

    @Override
    public Component getParentCompoentBySubComponentID(String subComponentID, String userID) {
        Component parent=getParentComponent(subComponentID,userID);
        return parent;
    }

    @Override
    public List<SubComponentList> getAllMySubComponent(String userID) {
        List<SubComponentList> subComponentLists=new ArrayList<>();
        List<Component> componentsOfUser=componentManager.listComponentByUser(userID);
        for(Component component:componentsOfUser){
            if(component.getSubComponentListSet()!=null){
                subComponentLists.addAll(component.getSubComponentListSet());
            }
        }

        return subComponentLists;
    }

    @Override
    public String getSubComponetName(String subID, User user) {
        String tableName=companyDatabaseIndexDao.getTableNameByCompanyAndTableStyle(user.getCompany().getCompanyId(),"SubComponentList");
        return subComponentListDao.getSubComponentName(subID,tableName);
    }
}
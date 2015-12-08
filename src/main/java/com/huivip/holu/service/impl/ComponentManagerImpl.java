package com.huivip.holu.service.impl;

import com.huivip.holu.dao.CompanyDatabaseIndexDao;
import com.huivip.holu.dao.ComponentDao;
import com.huivip.holu.dao.SubComponentListDao;
import com.huivip.holu.dao.UserDao;
import com.huivip.holu.model.Component;
import com.huivip.holu.model.Project;
import com.huivip.holu.model.User;
import com.huivip.holu.service.CompanyDatabaseIndexManager;
import com.huivip.holu.service.ComponentManager;
import com.huivip.holu.service.ProjectManager;
import com.huivip.holu.service.UserManager;
import com.huivip.holu.util.cache.Cache2kProvider;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import com.huivip.holu.webapp.helper.PaginatedListImpl;
import org.cache2k.Cache;
import org.cache2k.CacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service("componentManager")
@WebService(serviceName = "ComponentService", endpointInterface = "com.huivip.holu.service.ComponentManager")
public class ComponentManagerImpl extends GenericManagerImpl<Component, Long> implements ComponentManager {

    ComponentDao componentDao;
    @Autowired
    CompanyDatabaseIndexDao companyDatabaseIndexDao;
    @Autowired
    CompanyDatabaseIndexManager companyDatabaseIndexManager;
    @Autowired
    UserDao userDao;
    @Autowired
    UserManager userManager;
    @Autowired
    SubComponentListDao subComponentListDao;
    @Autowired
    ProjectManager projectManager;

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
        User user=userManager.getUserByUserID(userID);
        String tableName=companyDatabaseIndexManager.getComponentTableNameByCompany(user.getCompany().getCompanyId());
        String subTableName=companyDatabaseIndexManager.getSubComponentTableNameByCompany(user.getCompany().getCompanyId());
        List<Component> components=componentDao.listComponentByProject(projectID,tableName,list);
        for(Component component:components){
            component.setSubComponentListSet(new HashSet(subComponentListDao.getSubComponentListByComponentID(component.getComponentID(), subTableName, null)));
        }
        if(list!=null){
            list.setList(components);
        }
        return components;
    }

    @Override
    public Component getComponentByComponentID(String componentID,String userID) {
        User user=userManager.getUserByUserID(userID);
        String tableName=companyDatabaseIndexManager.getComponentTableNameByCompany(user.getCompany().getCompanyId());
        Component component=componentDao.getComponentByComponentID(componentID,tableName);
        if(component!=null){
            component.setSubComponentListSet(null);
        }
        return component;
    }

    @Override
    public List<Component> listComponentByUser(String userID) {
        List<Component> components=new ArrayList<>();
        List<Project> projectList=projectManager.getMyAllProject(userID);
        for (Project project:projectList){
            List<Component> componentsOfProject=getComponentListOfProject(userID,project.getProjectID());
            if(componentsOfProject!=null){
                components.addAll(componentsOfProject);
            }
        }
        return components;
    }

    @Override
    public List<Component> getComponentListOfProject(String userId, String projectID) {
        User user=userManager.getUserByUserID(userId);
        String tableName=companyDatabaseIndexManager.getComponentTableNameByCompany(user.getCompany().getCompanyId());
        List<Component> components=componentDao.listComponentByProject(projectID,tableName,null);
        for(Component component:components){
            component.setSubComponentListSet(null);
        }
        return components;
    }
}
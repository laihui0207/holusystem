package com.huivip.holu.service.impl;

import com.huivip.holu.dao.CompanyDatabaseIndexDao;
import com.huivip.holu.dao.SubComponentListDao;
import com.huivip.holu.dao.UserDao;
import com.huivip.holu.model.SubComponentList;
import com.huivip.holu.model.User;
import com.huivip.holu.service.SubComponentListManager;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
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
    public SubComponentList getSubComponentBySubComponentID(String subComponentID, String userID) {
        User user=userDao.getUserByUserID(userID);
        String tableName=companyDatabaseIndexDao.getTableNameByCompanyAndTableStyle(user.getCompany().getCompanyId(),"SubComponentList");
        return subComponentListDao.getSubComponentBySubComponentID(subComponentID,tableName);
    }
}
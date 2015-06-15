package com.huivip.holu.service.impl;

import com.huivip.holu.dao.UserGroupDao;
import com.huivip.holu.model.UserGroup;
import com.huivip.holu.service.UserGroupManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("userGroupManager")
@WebService(serviceName = "UserGroupService", endpointInterface = "com.huivip.holu.service.UserGroupManager")
public class UserGroupManagerImpl extends GenericManagerImpl<UserGroup, Long> implements UserGroupManager {
    UserGroupDao userGroupDao;

    @Autowired
    public UserGroupManagerImpl(UserGroupDao userGroupDao) {
        super(userGroupDao);
        this.userGroupDao = userGroupDao;
    }
}
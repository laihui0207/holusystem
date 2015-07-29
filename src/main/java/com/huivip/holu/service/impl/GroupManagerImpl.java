package com.huivip.holu.service.impl;

import com.huivip.holu.dao.GroupDao;
import com.huivip.holu.model.CustomGroup;
import com.huivip.holu.service.GroupManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

@Service("groupManager")
@WebService(serviceName = "GroupService", endpointInterface = "com.huivip.holu.service.GroupManager")
public class GroupManagerImpl extends GenericManagerImpl<CustomGroup, Long> implements GroupManager {
    GroupDao groupDao;

    @Autowired
    public GroupManagerImpl(GroupDao groupDao) {
        super(groupDao);
        this.groupDao = groupDao;
    }
}
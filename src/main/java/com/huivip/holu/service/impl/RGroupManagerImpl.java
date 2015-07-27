package com.huivip.holu.service.impl;

import com.huivip.holu.dao.RGroupDao;
import com.huivip.holu.model.CustomGroup;
import com.huivip.holu.service.RGroupManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

@Service("rGroupManager")
@WebService(serviceName = "RGroupService", endpointInterface = "com.huivip.holu.service.RGroupManager")
public class RGroupManagerImpl extends GenericManagerImpl<CustomGroup, Long> implements RGroupManager {
    RGroupDao rGroupDao;

    @Autowired
    public RGroupManagerImpl(RGroupDao rGroupDao) {
        super(rGroupDao);
        this.rGroupDao = rGroupDao;
    }
}
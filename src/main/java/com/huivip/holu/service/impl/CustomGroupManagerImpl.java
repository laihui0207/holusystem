package com.huivip.holu.service.impl;

import com.huivip.holu.dao.CustomGroupDao;
import com.huivip.holu.model.CustomGroup;
import com.huivip.holu.service.CustomGroupManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("customGroupManager")
@WebService(serviceName = "CustomGroupService", endpointInterface = "com.huivip.holu.service.CustomGroupManager")
public class CustomGroupManagerImpl extends GenericManagerImpl<CustomGroup, Long> implements CustomGroupManager {
    CustomGroupDao customGroupDao;

    @Autowired
    public CustomGroupManagerImpl(CustomGroupDao customGroupDao) {
        super(customGroupDao);
        this.customGroupDao = customGroupDao;
    }
}
package com.huivip.holu.service.impl;

import com.huivip.holu.dao.SubComponentListDao;
import com.huivip.holu.model.SubComponentList;
import com.huivip.holu.service.SubComponentListManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("subComponentListManager")
@WebService(serviceName = "SubComponentListService", endpointInterface = "com.huivip.holu.service.SubComponentListManager")
public class SubComponentListManagerImpl extends GenericManagerImpl<SubComponentList, Long> implements SubComponentListManager {
    SubComponentListDao subComponentListDao;

    @Autowired
    public SubComponentListManagerImpl(SubComponentListDao subComponentListDao) {
        super(subComponentListDao);
        this.subComponentListDao = subComponentListDao;
    }
}
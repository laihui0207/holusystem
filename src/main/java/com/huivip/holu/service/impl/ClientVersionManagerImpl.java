package com.huivip.holu.service.impl;

import com.huivip.holu.dao.ClientVersionDao;
import com.huivip.holu.model.ClientVersion;
import com.huivip.holu.service.ClientVersionManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("clientVersionManager")
@WebService(serviceName = "ClientVersionService", endpointInterface = "com.huivip.holu.service.ClientVersionManager")
public class ClientVersionManagerImpl extends GenericManagerImpl<ClientVersion, Long> implements ClientVersionManager {
    ClientVersionDao clientVersionDao;

    @Autowired
    public ClientVersionManagerImpl(ClientVersionDao clientVersionDao) {
        super(clientVersionDao);
        this.clientVersionDao = clientVersionDao;
    }
}
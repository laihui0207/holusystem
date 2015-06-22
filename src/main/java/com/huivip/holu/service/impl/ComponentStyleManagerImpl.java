package com.huivip.holu.service.impl;

import com.huivip.holu.dao.ComponentStyleDao;
import com.huivip.holu.model.ComponentStyle;
import com.huivip.holu.service.ComponentStyleManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("componentStyleManager")
@WebService(serviceName = "ComponentStyleService", endpointInterface = "com.huivip.holu.service.ComponentStyleManager")
public class ComponentStyleManagerImpl extends GenericManagerImpl<ComponentStyle, Long> implements ComponentStyleManager {
    ComponentStyleDao componentStyleDao;

    @Autowired
    public ComponentStyleManagerImpl(ComponentStyleDao componentStyleDao) {
        super(componentStyleDao);
        this.componentStyleDao = componentStyleDao;
    }
}
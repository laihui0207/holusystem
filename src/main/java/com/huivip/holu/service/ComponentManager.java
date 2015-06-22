package com.huivip.holu.service;

import com.huivip.holu.service.GenericManager;
import com.huivip.holu.model.Component;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface ComponentManager extends GenericManager<Component, Long> {
    
}
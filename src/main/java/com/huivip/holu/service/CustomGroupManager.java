package com.huivip.holu.service;

import com.huivip.holu.service.GenericManager;
import com.huivip.holu.model.CustomGroup;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface CustomGroupManager extends GenericManager<CustomGroup, Long> {
    
}
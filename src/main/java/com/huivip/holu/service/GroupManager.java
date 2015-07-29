package com.huivip.holu.service;

import com.huivip.holu.model.CustomGroup;

import javax.jws.WebService;

@WebService
public interface GroupManager extends GenericManager<CustomGroup, Long> {
    
}
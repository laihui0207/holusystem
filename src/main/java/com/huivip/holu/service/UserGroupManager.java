package com.huivip.holu.service;

import com.huivip.holu.service.GenericManager;
import com.huivip.holu.model.UserGroup;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface UserGroupManager extends GenericManager<UserGroup, Long> {
    
}
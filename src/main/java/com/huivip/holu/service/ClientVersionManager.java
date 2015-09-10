package com.huivip.holu.service;

import com.huivip.holu.service.GenericManager;
import com.huivip.holu.model.ClientVersion;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface ClientVersionManager extends GenericManager<ClientVersion, Long> {
    
}
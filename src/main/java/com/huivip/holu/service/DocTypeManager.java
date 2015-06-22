package com.huivip.holu.service;

import com.huivip.holu.service.GenericManager;
import com.huivip.holu.model.DocType;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface DocTypeManager extends GenericManager<DocType, Long> {
    
}
package com.huivip.holu.service;

import com.huivip.holu.service.GenericManager;
import com.huivip.holu.model.Documentation;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface DocumentationManager extends GenericManager<Documentation, Long> {
    
}
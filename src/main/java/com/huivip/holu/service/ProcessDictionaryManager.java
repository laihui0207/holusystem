package com.huivip.holu.service;

import com.huivip.holu.service.GenericManager;
import com.huivip.holu.model.ProcessDictionary;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface ProcessDictionaryManager extends GenericManager<ProcessDictionary, Long> {
    
}
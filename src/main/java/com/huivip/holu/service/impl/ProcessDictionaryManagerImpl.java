package com.huivip.holu.service.impl;

import com.huivip.holu.dao.ProcessDictionaryDao;
import com.huivip.holu.model.ProcessDictionary;
import com.huivip.holu.service.ProcessDictionaryManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("processDictionaryManager")
@WebService(serviceName = "ProcessDictionaryService", endpointInterface = "com.huivip.holu.service.ProcessDictionaryManager")
public class ProcessDictionaryManagerImpl extends GenericManagerImpl<ProcessDictionary, Long> implements ProcessDictionaryManager {
    ProcessDictionaryDao processDictionaryDao;

    @Autowired
    public ProcessDictionaryManagerImpl(ProcessDictionaryDao processDictionaryDao) {
        super(processDictionaryDao);
        this.processDictionaryDao = processDictionaryDao;
    }
}
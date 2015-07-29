package com.huivip.holu.service;

import com.huivip.holu.service.GenericManager;
import com.huivip.holu.model.CompanyDatabaseIndex;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface CompanyDatabaseIndexManager extends GenericManager<CompanyDatabaseIndex, Long> {
    
}
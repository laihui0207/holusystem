package com.huivip.holu.service;

import com.huivip.holu.service.GenericManager;
import com.huivip.holu.model.Company;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface CompanyManager extends GenericManager<Company, Long> {
    
}
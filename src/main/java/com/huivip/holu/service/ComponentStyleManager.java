package com.huivip.holu.service;

import com.huivip.holu.model.ComponentStyle;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface ComponentStyleManager extends GenericManager<ComponentStyle, Long> {
    List<ComponentStyle> getComponentStypeListByCompany(String companyId);
    
}
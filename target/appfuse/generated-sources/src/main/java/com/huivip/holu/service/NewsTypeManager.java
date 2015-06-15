package com.huivip.holu.service;

import com.huivip.holu.service.GenericManager;
import com.huivip.holu.model.NewsType;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface NewsTypeManager extends GenericManager<NewsType, Long> {
    
}
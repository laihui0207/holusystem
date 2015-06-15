package com.huivip.holu.service;

import com.huivip.holu.service.GenericManager;
import com.huivip.holu.model.News;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface NewsManager extends GenericManager<News, Long> {
    
}
package com.huivip.holu.service.impl;

import com.huivip.holu.dao.NewsTypeDao;
import com.huivip.holu.model.NewsType;
import com.huivip.holu.service.NewsTypeManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("newsTypeManager")
@WebService(serviceName = "NewsTypeService", endpointInterface = "com.huivip.holu.service.NewsTypeManager")
public class NewsTypeManagerImpl extends GenericManagerImpl<NewsType, Long> implements NewsTypeManager {
    NewsTypeDao newsTypeDao;

    @Autowired
    public NewsTypeManagerImpl(NewsTypeDao newsTypeDao) {
        super(newsTypeDao);
        this.newsTypeDao = newsTypeDao;
    }

    @Override
    public List<NewsType> newsTypeList() {
        return getAll();
    }
}
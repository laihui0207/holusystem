package com.huivip.holu.service.impl;

import com.huivip.holu.dao.NewsDao;
import com.huivip.holu.model.News;
import com.huivip.holu.service.NewsManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("newsManager")
@WebService(serviceName = "NewsService", endpointInterface = "com.huivip.holu.service.NewsManager")
public class NewsManagerImpl extends GenericManagerImpl<News, Long> implements NewsManager {
    NewsDao newsDao;

    @Autowired
    public NewsManagerImpl(NewsDao newsDao) {
        super(newsDao);
        this.newsDao = newsDao;
    }

    @Override
    public List<News> getNewss() {
        return newsDao.getAll();
    }

    @Override
    public News getNews(String id) {
        return get(Long.parseLong(id));
    }

    @Override
    public List<News> getNewsByType(String typeID) {
        if(null!=typeID && typeID.equalsIgnoreCase("all")){
            return getAll();
        }
        else {
           return newsDao.getNewsByType(typeID);
        }
    }
}
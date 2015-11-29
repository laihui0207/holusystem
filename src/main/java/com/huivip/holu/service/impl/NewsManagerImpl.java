package com.huivip.holu.service.impl;

import com.huivip.holu.dao.NewsDao;
import com.huivip.holu.model.News;
import com.huivip.holu.service.NewsManager;
import com.huivip.holu.util.cache.Cache2kProvider;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import com.huivip.holu.webapp.helper.PaginateListFactory;
import com.huivip.holu.webapp.helper.PaginatedListImpl;
import org.cache2k.Cache;
import org.cache2k.CacheBuilder;
import org.displaytag.properties.SortOrderEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("newsManager")
@WebService(serviceName = "NewsService", endpointInterface = "com.huivip.holu.service.NewsManager")
public class NewsManagerImpl extends GenericManagerImpl<News, Long> implements NewsManager {
    Logger logger= LoggerFactory.getLogger(this.getClass());
    NewsDao newsDao;
    @Autowired
    PaginateListFactory paginateListFactory;

    Cache<String,News> cache=null;


    @Autowired
    public NewsManagerImpl(NewsDao newsDao) {
        super(newsDao);
        this.newsDao = newsDao;
        cache=Cache2kProvider.getinstance().setCache(News.class,CacheBuilder.newCache(String.class,News.class).build());
    }

    @Override
    public List<News> getNewss(String pageIndex,String pageSize,String newsType) {
        ExtendedPaginatedList list= new PaginatedListImpl();
        list.setIndex(Integer.parseInt(pageIndex));
        list.setPageSize(Integer.parseInt(pageSize));
        list.setSortCriterion("createTime");
        list.setSortDirection(SortOrderEnum.DESCENDING);
        String cacheKey=News.LIST_CACHE+newsType+"_"+pageIndex+"_"+pageSize;
        maintainCacheKey(News.LIST_NEWS_CACHE_KEY,cacheKey);
        ExtendedPaginatedList listFromCache=listCache.peek(cacheKey);
        if(listFromCache==null){
            if(newsType.equalsIgnoreCase("all")) {
                newsDao.getAllPagable(list);
            }
            else {
                newsDao.getNewsByType(newsType,list);
            }
            listCache.put(cacheKey,list);
        }
        else {
            list=listFromCache;
        }

        return list.getList();
    }

    @Override
    public List<News> getImportantNewss(String pageIndex, String pageSize) {
        ExtendedPaginatedList list= new PaginatedListImpl();
        list.setIndex(Integer.parseInt(pageIndex));
        list.setPageSize(Integer.parseInt(pageSize));
        list.setSortCriterion("createTime");
        list.setSortDirection(SortOrderEnum.DESCENDING);
        String cacheKey=News.LIST_CACHE+"Level_"+pageIndex+"_"+pageSize;
        maintainCacheKey(News.LIST_NEWS_CACHE_KEY,cacheKey);
        ExtendedPaginatedList listFromCache=listCache.peek(cacheKey);
        if(listFromCache==null){
            newsDao.getNewsByLevel(list);
            listCache.put(cacheKey,list);
        }
        else {
            list=listFromCache;
        }
        return list.getList();
    }


    @Override
    public News getNews(String id,String clientType,HttpServletRequest request) {
        News news=cache.peek(id);
        if(news==null){
            logger.debug("get news:"+id+" return null");
            news=get(Long.parseLong(id));
            cache.put(id,news);
        }
        else {
            logger.debug("get news:"+id+" success!!!");
        }
        return news;
    }

    @Override
    public List<News> getNewsByType(String typeID) {
        if(null!=typeID && typeID.equalsIgnoreCase("all")){
            return getAll();
        }
        else {
           return newsDao.getNewsByType(typeID,null);
        }
    }

}
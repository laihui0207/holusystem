package com.huivip.holu.service.impl;

import com.huivip.holu.dao.NewsDao;
import com.huivip.holu.model.News;
import com.huivip.holu.service.NewsManager;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import com.huivip.holu.webapp.helper.PaginateListFactory;
import com.huivip.holu.webapp.helper.PaginatedListImpl;
import org.displaytag.properties.SortOrderEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.List;

@Service("newsManager")
@WebService(serviceName = "NewsService", endpointInterface = "com.huivip.holu.service.NewsManager")
public class NewsManagerImpl extends GenericManagerImpl<News, Long> implements NewsManager {
    NewsDao newsDao;
    @Autowired
    PaginateListFactory paginateListFactory;

    @Autowired
    public NewsManagerImpl(NewsDao newsDao) {
        super(newsDao);
        this.newsDao = newsDao;
    }

    @Override
    public List<News> getNewss(String pageIndex,String pageSize,String newsType) {
        ExtendedPaginatedList list= new PaginatedListImpl();
        list.setIndex(Integer.parseInt(pageIndex));
        list.setPageSize(Integer.parseInt(pageSize));
        list.setSortCriterion("createTime");
        list.setSortDirection(SortOrderEnum.DESCENDING);
        if(newsType.equalsIgnoreCase("all")) {
            newsDao.getAllPagable(list);
        }
        else {
            newsDao.getNewsByType(newsType,list);
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
        newsDao.getNewsByLevel(list);
        return list.getList();
    }

    @Override
    public News getNews(String id,String clientType,HttpServletRequest request) {
        News news=get(Long.parseLong(id));
        String content=news.getContent();

        String contextPath=request.getContextPath();
        String requestUri = request.getRequestURL().toString();
        String contextURL=requestUri.substring(0, requestUri.indexOf(contextPath));

        /*content= ImageAndVideo.handleImage(content,contextURL);
        //content=ImageAndVideo.handleVideo(content,contextPath,clientType);

        news.setContent(content);*/
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
package com.huivip.holu.webapp.controller;

import com.huivip.holu.model.News;
import com.huivip.holu.service.NewsManager;
import com.huivip.holu.util.cache.Cache2kProvider;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import com.huivip.holu.webapp.helper.PaginateListFactory;
import org.cache2k.Cache;
import org.displaytag.properties.SortOrderEnum;
import org.hibernate.search.SearchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/newss*")
public class NewsController {
    Logger logger= LoggerFactory.getLogger(this.getClass());
    private NewsManager newsManager;
    @Autowired
    PaginateListFactory paginateListFactory;
    Cache<String,News> cache= Cache2kProvider.getinstance().getCache(News.class);

    @Autowired
    public void setNewsManager(NewsManager newsManager) {
        this.newsManager = newsManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query,HttpServletRequest request)
    throws Exception {
        ExtendedPaginatedList list=paginateListFactory.getPaginatedListFromRequest(request);
        list.setSortCriterion("createTime");
        list.setSortDirection(SortOrderEnum.DESCENDING);
        Model model = new ExtendedModelMap();
        try {

            newsManager.search(query, News.class, list);
            model.addAttribute("newsList",list);
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            newsManager.getAllPageable(list);
            model.addAttribute(list);
        }
        model.addAttribute("newsList",list);
        return model;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/view/{id}",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String viewNews(@PathVariable("id")String id) {
        News news =cache.peek(id);
        if(news==null){
            logger.debug("Get news from cache return null");
            news=newsManager.get(Long.parseLong(id));
            cache.put(id,news);
        }
        return news.getContent();
    }
}

package com.huivip.holu.webapp.controller;

import com.huivip.holu.model.News;
import com.huivip.holu.service.NewsManager;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import com.huivip.holu.webapp.helper.PaginateListFactory;
import org.hibernate.search.SearchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/newss*")
public class NewsController {
    private NewsManager newsManager;
    @Autowired
    PaginateListFactory paginateListFactory;

    @Autowired
    public void setNewsManager(NewsManager newsManager) {
        this.newsManager = newsManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query,HttpServletRequest request)
    throws Exception {
        ExtendedPaginatedList list=paginateListFactory.getPaginatedListFromRequest(request);
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
        News news = newsManager.get(Long.parseLong(id));
        return news.getContent();
    }
}

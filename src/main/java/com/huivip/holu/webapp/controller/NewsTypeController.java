package com.huivip.holu.webapp.controller;

import com.huivip.holu.dao.SearchException;
import com.huivip.holu.model.NewsType;
import com.huivip.holu.service.NewsTypeManager;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import com.huivip.holu.webapp.helper.PaginateListFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/newsTypes*")
public class NewsTypeController {
    private NewsTypeManager newsTypeManager;
    @Autowired
    PaginateListFactory paginateListFactory;
    @Autowired
    public void setNewsTypeManager(NewsTypeManager newsTypeManager) {
        this.newsTypeManager = newsTypeManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query,HttpServletRequest request)
    throws Exception {
        Model model = new ExtendedModelMap();
        ExtendedPaginatedList list=paginateListFactory.getPaginatedListFromRequest(request);
        try {
            newsTypeManager.search(query, NewsType.class,list);
            model.addAttribute("newsTypeList",list);
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            newsTypeManager.getAllPageable(list);
            model.addAttribute("newsTypeList",list);
        }
        return model;
    }
}

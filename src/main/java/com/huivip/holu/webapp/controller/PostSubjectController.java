package com.huivip.holu.webapp.controller;

import com.huivip.holu.dao.SearchException;
import com.huivip.holu.model.PostSubject;
import com.huivip.holu.service.PostSubjectManager;
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
@RequestMapping("/postSubjects*")
public class PostSubjectController {
    private PostSubjectManager postSubjectManager;
    @Autowired
    PaginateListFactory paginateListFactory;
    @Autowired
    public void setPostSubjectManager(PostSubjectManager postSubjectManager) {
        this.postSubjectManager = postSubjectManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query,HttpServletRequest request)
    throws Exception {
        Model model = new ExtendedModelMap();
        ExtendedPaginatedList list=paginateListFactory.getPaginatedListFromRequest(request);
        try {
            postSubjectManager.search(query, PostSubject.class,list);
            model.addAttribute("postSubjectList",list);
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            postSubjectManager.getAllPageable(list);
            model.addAttribute("postSubjectList",list);
        }
        return model;
    }
}

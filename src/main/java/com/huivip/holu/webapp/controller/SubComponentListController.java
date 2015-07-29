package com.huivip.holu.webapp.controller;

import com.huivip.holu.dao.SearchException;
import com.huivip.holu.service.SubComponentListManager;
import com.huivip.holu.model.SubComponentList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/subComponentLists*")
public class SubComponentListController {
    private SubComponentListManager subComponentListManager;

    @Autowired
    public void setSubComponentListManager(SubComponentListManager subComponentListManager) {
        this.subComponentListManager = subComponentListManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(subComponentListManager.search(query, SubComponentList.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(subComponentListManager.getAll());
        }
        return model;
    }
}

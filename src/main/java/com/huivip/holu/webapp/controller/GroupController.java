package com.huivip.holu.webapp.controller;

import com.huivip.holu.dao.SearchException;
import com.huivip.holu.service.GroupManager;
import com.huivip.holu.model.CustomGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/groups*")
public class GroupController {
    private GroupManager groupManager;

    @Autowired
    public void setGroupManager(GroupManager groupManager) {
        this.groupManager = groupManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(groupManager.search(query, CustomGroup.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(groupManager.getAll());
        }
        return model;
    }
}

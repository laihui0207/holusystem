package com.huivip.holu.webapp.controller;

import com.huivip.holu.dao.SearchException;
import com.huivip.holu.service.ProjectIndexManager;
import com.huivip.holu.model.ProjectIndex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/projectIndices*")
public class ProjectIndexController {
    private ProjectIndexManager projectIndexManager;

    @Autowired
    public void setProjectIndexManager(ProjectIndexManager projectIndexManager) {
        this.projectIndexManager = projectIndexManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(projectIndexManager.search(query, ProjectIndex.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(projectIndexManager.getAll());
        }
        return model;
    }
}

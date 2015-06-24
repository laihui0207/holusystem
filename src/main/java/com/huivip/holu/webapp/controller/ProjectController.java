package com.huivip.holu.webapp.controller;

import com.huivip.holu.dao.SearchException;
import com.huivip.holu.model.Project;
import com.huivip.holu.model.User;
import com.huivip.holu.service.ProjectManager;
import com.huivip.holu.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/projects*")
public class ProjectController {
    private ProjectManager projectManager;
    @Autowired
    private UserManager userManager;

    @Autowired
    public void setProjectManager(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(projectManager.search(query, Project.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(projectManager.getAll());
        }
        return model;
    }
    @RequestMapping(method = RequestMethod.GET,value = "MyProjects")
    public ModelAndView myProject(HttpServletRequest request){
        final User cleanUser = userManager.getUserByUsername(
                request.getRemoteUser());
        List<Project> projectList=projectManager.getProjectListByCompany(cleanUser.getCompany().getId().toString());
        ModelAndView view=new ModelAndView("projects");
        view.addObject(projectList);
        return view;
    }
}

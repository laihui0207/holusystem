package com.huivip.holu.webapp.controller;

import com.huivip.holu.model.User;
import com.huivip.holu.service.ProjectManager;
import com.huivip.holu.service.UserManager;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import com.huivip.holu.webapp.helper.PaginateListFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/projects*")
public class ProjectController {
    private ProjectManager projectManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private PaginateListFactory paginateListFactory;

    @Autowired
    public void setProjectManager(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(HttpServletRequest request)
    throws Exception {
        Model model = new ExtendedModelMap();
        ExtendedPaginatedList list=paginateListFactory.getPaginatedListFromRequest(request);
        User currentUser=userManager.getUserByLoginCode(request.getRemoteUser());
        String parentId=request.getParameter("parentID");
        projectManager.getProjectByUserID(currentUser.getUserID(),parentId,list);
        /*try {
            model.addAttribute(projectManager.search(query, Project.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(projectManager.getAll());
        }*/
        model.addAttribute("projectList",list);
        return model;
    }

}

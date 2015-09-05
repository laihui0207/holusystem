package com.huivip.holu.webapp.controller;

import com.huivip.holu.dao.SearchException;
import com.huivip.holu.model.Component;
import com.huivip.holu.model.Project;
import com.huivip.holu.model.User;
import com.huivip.holu.service.ComponentManager;
import com.huivip.holu.service.ComponentStyleManager;
import com.huivip.holu.service.ProjectManager;
import com.huivip.holu.service.UserManager;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import com.huivip.holu.webapp.helper.PaginateListFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/components*")
public class ComponentController {
    private ComponentManager componentManager;
    @Autowired
    private ProjectManager projectManager;
    @Autowired
    private ComponentStyleManager componentStyleManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private PaginateListFactory paginateListFactory;

    @Autowired
    public void setComponentManager(ComponentManager componentManager) {
        this.componentManager = componentManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(componentManager.search(query, Component.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(componentManager.getAll());
        }
        return model;
    }

    @RequestMapping(method = RequestMethod.GET,value = "{id}/Component")
    public ModelAndView showComponentByProject(@PathVariable("id") String projectID,HttpServletRequest request){
        ModelAndView view=new ModelAndView("components");
        ExtendedPaginatedList list=paginateListFactory.getPaginatedListFromRequest(request);
        User currentUser=userManager.getUserByLoginCode(request.getRemoteUser());
        componentManager.listComponentByProject(projectID,currentUser.getUserID(),list);
        Project project=projectManager.getProjectByprojectID(projectID);
        view.addObject("componentList",list);
        view.addObject("project",project);
        return view;
    }

}

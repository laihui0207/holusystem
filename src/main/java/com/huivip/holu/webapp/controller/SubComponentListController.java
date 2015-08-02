package com.huivip.holu.webapp.controller;

import com.huivip.holu.dao.SearchException;
import com.huivip.holu.model.SubComponentList;
import com.huivip.holu.model.User;
import com.huivip.holu.service.SubComponentListManager;
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
@RequestMapping("/subComponentLists*")
public class SubComponentListController {
    private SubComponentListManager subComponentListManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private PaginateListFactory paginateListFactory;

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
    @RequestMapping(method = RequestMethod.GET,value = "{compoentID}/subList")
    public ModelAndView listSubComponentList(@PathVariable("compoentID")String componentID,HttpServletRequest request){
        ModelAndView view=new ModelAndView("subComponentLists");
        ExtendedPaginatedList list=paginateListFactory.getPaginatedListFromRequest(request);
        User currentUser=userManager.getUserByLoginCode(request.getRemoteUser());
        subComponentListManager.getSubComponentListByComponentID(componentID,currentUser.getUserID(),list);
        view.addObject("subComponentListList",list);
        return view;
    }
}

package com.huivip.holu.webapp.controller;

import com.huivip.holu.dao.SearchException;
import com.huivip.holu.model.UserGroup;
import com.huivip.holu.service.UserGroupManager;
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
@RequestMapping("/userGroups*")
public class UserGroupController {
    private UserGroupManager userGroupManager;
    @Autowired
    PaginateListFactory paginateListFactory;
    @Autowired
    public void setUserGroupManager(UserGroupManager userGroupManager) {
        this.userGroupManager = userGroupManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query,HttpServletRequest request)
    throws Exception {
        Model model = new ExtendedModelMap();
        ExtendedPaginatedList list=paginateListFactory.getPaginatedListFromRequest(request);
        try {
/*            if(request.isUserInRole(Constants.ADMIN_ROLE)){*/
                userGroupManager.search(query, UserGroup.class,list);
                model.addAttribute("userGroupList",list);
            /*}
            else {

            }*/
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            userGroupManager.getAllPageable(list);
            model.addAttribute("userGroupList",list);
        }
        return model;
    }
}

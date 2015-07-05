package com.huivip.holu.webapp.controller;

import com.huivip.holu.dao.SearchException;
import com.huivip.holu.model.ComponentStyle;
import com.huivip.holu.model.User;
import com.huivip.holu.service.ComponentStyleManager;
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
@RequestMapping("/componentStyles*")
public class ComponentStyleController {
    private ComponentStyleManager componentStyleManager;
    @Autowired
    private UserManager userManager;

    @Autowired
    public void setComponentStyleManager(ComponentStyleManager componentStyleManager) {
        this.componentStyleManager = componentStyleManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(componentStyleManager.search(query, ComponentStyle.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(componentStyleManager.getAll());
        }
        return model;
    }

    @RequestMapping(method = RequestMethod.GET, value = "processlist")
    public ModelAndView processListByStyleAndCompany(@RequestParam("styleName") String styleName,
                                                     @RequestParam("companyId") String companyId,HttpServletRequest request){
        ModelAndView view=new ModelAndView("processListOfStyleAndCompany");
        List<ComponentStyle> componentStyleList=componentStyleManager.getProcessListByCompanyAndStyleName(styleName,companyId);
        view.addObject("componentStyleList",componentStyleList);

        final User cleanUser = userManager.getUserByUsername(
                request.getRemoteUser());
        view.addObject("user",cleanUser);
        return view;
    }
}

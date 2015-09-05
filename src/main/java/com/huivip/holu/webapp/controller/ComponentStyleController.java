package com.huivip.holu.webapp.controller;

import com.huivip.holu.dao.SearchException;
import com.huivip.holu.model.Component;
import com.huivip.holu.model.ComponentStyle;
import com.huivip.holu.model.SubComponentList;
import com.huivip.holu.model.User;
import com.huivip.holu.service.ComponentManager;
import com.huivip.holu.service.ComponentStyleManager;
import com.huivip.holu.service.SubComponentListManager;
import com.huivip.holu.service.UserManager;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import com.huivip.holu.webapp.helper.PaginateListFactory;
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
import java.util.Locale;

@Controller
@RequestMapping("/componentStyles*")
public class ComponentStyleController {
    private ComponentStyleManager componentStyleManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private ComponentManager componentManager;
    @Autowired
    private SubComponentListManager subComponentListManager;
    @Autowired
    private PaginateListFactory paginateListFactory;

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
    public ModelAndView processListByStyleAndCompany(@RequestParam("styleID") String styleId,
                                                     @RequestParam("companyId") String companyId,HttpServletRequest request){
        ModelAndView view=new ModelAndView("processListOfStyleAndCompany");
        String componentID=request.getParameter("cid");
        String componentType=request.getParameter("type");
        ExtendedPaginatedList list=paginateListFactory.getPaginatedListFromRequest(request);
        final User cleanUser = userManager.getUserByLoginCode(
                request.getRemoteUser());
        Component parent=null;
        SubComponentList subComponentList=null;
        if(componentType.equalsIgnoreCase("parent")){
            parent=componentManager.getComponentByComponentID(componentID,cleanUser.getUserID());
        }
        else  if(componentType.equalsIgnoreCase("sub")){
            subComponentList=subComponentListManager.getSubComponentBySubComponentID(componentID,cleanUser.getUserID());
            if(null!=subComponentList){
                parent=subComponentListManager.getParentComponent(componentID,cleanUser.getUserID());
            }
        }
        List<ComponentStyle> componentStyleList=componentStyleManager.getProcessListByCompanyAndStyleName(styleId,companyId,cleanUser.getUserID(),componentID,null);
        Locale locale = request.getLocale();
        view.addObject("language",locale.getDisplayLanguage());
        view.addObject("componentStyleList",componentStyleList);
        view.addObject("component",parent);
        view.addObject("subComponent",subComponentList);
        view.addObject("componentID",componentID);
        view.addObject("componentType",componentType);
        view.addObject("user",cleanUser);
        return view;
    }
}

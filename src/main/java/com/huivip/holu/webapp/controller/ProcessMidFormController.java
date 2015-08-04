package com.huivip.holu.webapp.controller;

import com.huivip.holu.model.*;
import com.huivip.holu.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/processMidform*")
public class ProcessMidFormController extends BaseFormController {
    private ProcessMidManager processMidManager = null;
    @Autowired
    private ProjectManager projectManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private ComponentManager componentManager;
    @Autowired
    private SubComponentListManager subComponentListManager;
    @Autowired
    private ComponentStyleManager componentStyleManager;

    @Autowired
    public void setProcessMidManager(ProcessMidManager processMidManager) {
        this.processMidManager = processMidManager;
    }

    public ProcessMidFormController() {
        setCancelView("redirect:processMids");
        setSuccessView("redirect:processMids");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected ProcessMid showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return processMidManager.get(new Long(id));
        }

        return new ProcessMid();
    }
    @RequestMapping(method = RequestMethod.GET,value ="Confirm")
    public ModelAndView confirmProcess(@RequestParam("SPID")String styleProcessId,
                                       @RequestParam("componentID")String componentID,
                                       @RequestParam("type") String componentType,
                                @RequestParam("projectID")String projectID,HttpServletRequest request){
        ModelAndView view=new ModelAndView("processconfirm");
        Locale locale=request.getLocale();
        User currentUser=userManager.getUserByLoginCode(request.getRemoteUser());
        Project project=projectManager.getProjectByprojectID(projectID);
        Component parent=null;
        SubComponentList subComponentList=null;
        if(componentType.equalsIgnoreCase("parent")){
            parent=componentManager.getComponentByComponentID(componentID,currentUser.getUserID());
            view.addObject("componentID",parent.getComponentID());
        }
        else  if(componentType.equalsIgnoreCase("sub")){
            subComponentList=subComponentListManager.getSubComponentBySubComponentID(componentID,currentUser.getUserID());
            parent=subComponentList.getParentComponent();
            view.addObject("componentID",subComponentList.getSubComponentID());
        }
        ProcessMid processMid=processMidManager.getProcessMid(componentID,styleProcessId,currentUser.getUserID());
        if(null!=processMid){
            view.addObject("confirmed",true);
            saveError(request,getText("processMid.confirmed",locale));
        }
        else {
            processMid=new ProcessMid();
        }
        ComponentStyle componentStyle=componentStyleManager.getComponentProcessByProcessID(styleProcessId);
        view.addObject("component",parent);
        view.addObject("subComponent",subComponentList);
        view.addObject("user",currentUser);
        view.addObject("project",project);
        view.addObject("componentStyle",componentStyle);
        view.addObject("processMid",processMid);
        return view;
    }
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(ProcessMid processMid, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(processMid, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "processMidform";
            }
        }
        User currentUser=userManager.getUserByLoginCode(request.getRemoteUser());
        log.debug("entering 'onSubmit' method...");

        boolean isNew = (processMid.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            processMidManager.remove(processMid.getId());
            saveMessage(request, getText("processMid.deleted", locale));
        } else {
            processMid.setUser(currentUser);
            processMidManager.save(processMid,currentUser.getUserID());
            String key = (isNew) ? "processMid.added" : "processMid.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:processMidform?id=" + processMid.getId();
            }
        }

        return success;
    }
}

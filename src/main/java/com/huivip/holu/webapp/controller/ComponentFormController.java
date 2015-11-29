package com.huivip.holu.webapp.controller;

import com.huivip.holu.model.Component;
import com.huivip.holu.model.ComponentStyle;
import com.huivip.holu.model.Project;
import com.huivip.holu.model.User;
import com.huivip.holu.service.ComponentManager;
import com.huivip.holu.service.ComponentStyleManager;
import com.huivip.holu.service.ProjectManager;
import com.huivip.holu.service.UserManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/componentform*")
public class ComponentFormController extends BaseFormController {
    private ComponentManager componentManager = null;
    @Autowired
    private UserManager userManager;
    @Autowired
    private ProjectManager projectManager;
    @Autowired
    private ComponentStyleManager componentStyleManager;

    @Autowired
    public void setComponentManager(ComponentManager componentManager) {
        this.componentManager = componentManager;
    }

    public ComponentFormController() {
        setCancelView("redirect:components");
        setSuccessView("redirect:components");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET,value = "{id}")
    protected ModelAndView showForm(@PathVariable("id") String projectId,HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        Project project=projectManager.get(Long.parseLong(projectId));
        List<ComponentStyle> componentStyles=componentStyleManager.getComponentStypeListByCompany(project.getCompany().getId().toString());
        ModelAndView view=new ModelAndView("componentform");
        view.addObject("componentStyleList",componentStyles);
        view.addObject("project",project);
        if (!StringUtils.isBlank(id)) {
            view.addObject(componentManager.get(new Long(id)));
            return view;
        }
        Component component=new Component();
        component.setProject(project);
        view.addObject(component);
        return view;
    }

    @RequestMapping(method = RequestMethod.POST,value = "{id}")
    public String onSubmit(@PathVariable("id") String projectID,Component component, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return "redirect:/components/"+projectID+"/Component";
        }
        Project project=projectManager.get(Long.parseLong(projectID));
        if (validator != null) { // validator is null during testing
            validator.validate(component, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "componentform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (component.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            componentManager.remove(component.getId());
            saveMessage(request, getText("component.deleted", locale));
        } else {
            final User cleanUser = getUserManager().getUserByLoginCode(
                    request.getRemoteUser());
            component.setCreater(cleanUser);
            component.setProject(project);
            component.setCreateDate(new Timestamp(new Date().getTime()));
            componentManager.save(component);
            String key = (isNew) ? "component.added" : "component.updated";
            saveMessage(request, getText(key, locale));

            /*if (!isNew) {
                success = "redirect:componentform?id=" + component.getId();
            }*/
        }

        return "redirect:/components/"+projectID+"/Component";
    }
 /*   @InitBinder
    public void binder(WebDataBinder binder) {binder.registerCustomEditor(Timestamp.class,
            new PropertyEditorSupport() {
                public void setAsText(String value) {
                    try {
                        Date parsedDate = new SimpleDateFormat("MM/dd/yyyy").parse(value);
                        setValue(new Timestamp(parsedDate.getTime()));
                    } catch (ParseException e) {
                        setValue(null);
                    }
                }
            });
    }*/
    @ModelAttribute("userList")
    public List<User> userList(){
        return userManager.getAll();
    }
    @ModelAttribute("projectList")
    public List<Project> projectList(){
        return projectManager.getAll();
    }
}

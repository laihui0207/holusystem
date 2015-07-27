package com.huivip.holu.webapp.controller;

import com.huivip.holu.model.Project;
import com.huivip.holu.service.ProjectManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/projectform*")
public class ProjectFormController extends BaseFormController {
    private ProjectManager projectManager = null;

    @Autowired
    public void setProjectManager(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    public ProjectFormController() {
        setCancelView("redirect:projects");
        setSuccessView("redirect:projects");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Project showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return projectManager.get(new Long(id));
        }

        return new Project();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Project project, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(project, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "projectform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (project.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            projectManager.remove(project.getId());
            saveMessage(request, getText("project.deleted", locale));
        } else {
            projectManager.save(project);
            String key = (isNew) ? "project.added" : "project.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:projectform?id=" + project.getId();
            }
        }

        return success;
    }
}

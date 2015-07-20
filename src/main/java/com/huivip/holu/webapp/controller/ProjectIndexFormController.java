package com.huivip.holu.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.huivip.holu.service.ProjectIndexManager;
import com.huivip.holu.model.ProjectIndex;
import com.huivip.holu.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/projectIndexform*")
public class ProjectIndexFormController extends BaseFormController {
    private ProjectIndexManager projectIndexManager = null;

    @Autowired
    public void setProjectIndexManager(ProjectIndexManager projectIndexManager) {
        this.projectIndexManager = projectIndexManager;
    }

    public ProjectIndexFormController() {
        setCancelView("redirect:projectIndices");
        setSuccessView("redirect:projectIndices");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected ProjectIndex showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return projectIndexManager.get(new Long(id));
        }

        return new ProjectIndex();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(ProjectIndex projectIndex, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(projectIndex, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "projectIndexform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (projectIndex.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            projectIndexManager.remove(projectIndex.getId());
            saveMessage(request, getText("projectIndex.deleted", locale));
        } else {
            projectIndexManager.save(projectIndex);
            String key = (isNew) ? "projectIndex.added" : "projectIndex.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:projectIndexform?id=" + projectIndex.getId();
            }
        }

        return success;
    }
}

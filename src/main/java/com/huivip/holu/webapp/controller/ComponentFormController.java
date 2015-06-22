package com.huivip.holu.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.huivip.holu.service.ComponentManager;
import com.huivip.holu.model.Component;
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
@RequestMapping("/componentform*")
public class ComponentFormController extends BaseFormController {
    private ComponentManager componentManager = null;

    @Autowired
    public void setComponentManager(ComponentManager componentManager) {
        this.componentManager = componentManager;
    }

    public ComponentFormController() {
        setCancelView("redirect:components");
        setSuccessView("redirect:components");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Component showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return componentManager.get(new Long(id));
        }

        return new Component();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Component component, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

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
            componentManager.save(component);
            String key = (isNew) ? "component.added" : "component.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:componentform?id=" + component.getId();
            }
        }

        return success;
    }
}

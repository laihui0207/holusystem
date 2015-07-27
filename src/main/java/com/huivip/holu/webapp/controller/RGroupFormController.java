package com.huivip.holu.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.huivip.holu.service.RGroupManager;
import com.huivip.holu.model.CustomGroup;

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
@RequestMapping("/rGroupform*")
public class RGroupFormController extends BaseFormController {
    private RGroupManager rGroupManager = null;

    @Autowired
    public void setRGroupManager(RGroupManager rGroupManager) {
        this.rGroupManager = rGroupManager;
    }

    public RGroupFormController() {
        setCancelView("redirect:rGroups");
        setSuccessView("redirect:rGroups");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected CustomGroup showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return rGroupManager.get(new Long(id));
        }

        return new CustomGroup();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(CustomGroup customGroup, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(customGroup, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "rGroupform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (customGroup.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            rGroupManager.remove(customGroup.getId());
            saveMessage(request, getText("rGroup.deleted", locale));
        } else {
            rGroupManager.save(customGroup);
            String key = (isNew) ? "rGroup.added" : "rGroup.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:rGroupform?id=" + customGroup.getId();
            }
        }

        return success;
    }
}

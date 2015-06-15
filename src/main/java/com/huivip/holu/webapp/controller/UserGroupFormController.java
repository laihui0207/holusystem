package com.huivip.holu.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.huivip.holu.service.UserGroupManager;
import com.huivip.holu.model.UserGroup;
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
@RequestMapping("/userGroupform*")
public class UserGroupFormController extends BaseFormController {
    private UserGroupManager userGroupManager = null;

    @Autowired
    public void setUserGroupManager(UserGroupManager userGroupManager) {
        this.userGroupManager = userGroupManager;
    }

    public UserGroupFormController() {
        setCancelView("redirect:userGroups");
        setSuccessView("redirect:userGroups");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected UserGroup showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return userGroupManager.get(new Long(id));
        }

        return new UserGroup();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(UserGroup userGroup, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(userGroup, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "userGroupform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (userGroup.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            userGroupManager.remove(userGroup.getId());
            saveMessage(request, getText("userGroup.deleted", locale));
        } else {
            userGroupManager.save(userGroup);
            String key = (isNew) ? "userGroup.added" : "userGroup.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:userGroupform?id=" + userGroup.getId();
            }
        }

        return success;
    }
}

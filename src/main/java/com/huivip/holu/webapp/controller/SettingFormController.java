package com.huivip.holu.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.huivip.holu.service.SettingManager;
import com.huivip.holu.model.Setting;
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
@RequestMapping("/settingform*")
public class SettingFormController extends BaseFormController {
    private SettingManager settingManager = null;

    @Autowired
    public void setSettingManager(SettingManager settingManager) {
        this.settingManager = settingManager;
    }

    public SettingFormController() {
        setCancelView("redirect:settings");
        setSuccessView("redirect:settings");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Setting showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return settingManager.get(new Long(id));
        }

        return new Setting();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Setting setting, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(setting, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "settingform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (setting.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            settingManager.remove(setting.getId());
            saveMessage(request, getText("setting.deleted", locale));
        } else {
            settingManager.save(setting);
            String key = (isNew) ? "setting.added" : "setting.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:settingform?id=" + setting.getId();
            }
        }

        return success;
    }
}

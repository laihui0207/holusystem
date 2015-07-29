package com.huivip.holu.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.huivip.holu.service.SubComponentListManager;
import com.huivip.holu.model.SubComponentList;
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
@RequestMapping("/subComponentListform*")
public class SubComponentListFormController extends BaseFormController {
    private SubComponentListManager subComponentListManager = null;

    @Autowired
    public void setSubComponentListManager(SubComponentListManager subComponentListManager) {
        this.subComponentListManager = subComponentListManager;
    }

    public SubComponentListFormController() {
        setCancelView("redirect:subComponentLists");
        setSuccessView("redirect:subComponentLists");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected SubComponentList showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return subComponentListManager.get(new Long(id));
        }

        return new SubComponentList();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(SubComponentList subComponentList, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(subComponentList, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "subComponentListform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (subComponentList.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            subComponentListManager.remove(subComponentList.getId());
            saveMessage(request, getText("subComponentList.deleted", locale));
        } else {
            subComponentListManager.save(subComponentList);
            String key = (isNew) ? "subComponentList.added" : "subComponentList.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:subComponentListform?id=" + subComponentList.getId();
            }
        }

        return success;
    }
}

package com.huivip.holu.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.huivip.holu.service.CompanyDatabaseIndexManager;
import com.huivip.holu.model.CompanyDatabaseIndex;
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
@RequestMapping("/companyDatabaseIndexform*")
public class CompanyDatabaseIndexFormController extends BaseFormController {
    private CompanyDatabaseIndexManager companyDatabaseIndexManager = null;

    @Autowired
    public void setCompanyDatabaseIndexManager(CompanyDatabaseIndexManager companyDatabaseIndexManager) {
        this.companyDatabaseIndexManager = companyDatabaseIndexManager;
    }

    public CompanyDatabaseIndexFormController() {
        setCancelView("redirect:companyDatabaseIndices");
        setSuccessView("redirect:companyDatabaseIndices");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected CompanyDatabaseIndex showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return companyDatabaseIndexManager.get(new Long(id));
        }

        return new CompanyDatabaseIndex();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(CompanyDatabaseIndex companyDatabaseIndex, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(companyDatabaseIndex, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "companyDatabaseIndexform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (companyDatabaseIndex.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            companyDatabaseIndexManager.remove(companyDatabaseIndex.getId());
            saveMessage(request, getText("companyDatabaseIndex.deleted", locale));
        } else {
            companyDatabaseIndexManager.save(companyDatabaseIndex);
            String key = (isNew) ? "companyDatabaseIndex.added" : "companyDatabaseIndex.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:companyDatabaseIndexform?id=" + companyDatabaseIndex.getId();
            }
        }

        return success;
    }
}

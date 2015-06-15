package com.huivip.holu.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.huivip.holu.service.CompanyManager;
import com.huivip.holu.model.Company;
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
@RequestMapping("/companyform*")
public class CompanyFormController extends BaseFormController {
    private CompanyManager companyManager = null;

    @Autowired
    public void setCompanyManager(CompanyManager companyManager) {
        this.companyManager = companyManager;
    }

    public CompanyFormController() {
        setCancelView("redirect:companies");
        setSuccessView("redirect:companies");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Company showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return companyManager.get(new Long(id));
        }

        return new Company();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Company company, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(company, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "companyform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (company.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            companyManager.remove(company.getId());
            saveMessage(request, getText("company.deleted", locale));
        } else {
            companyManager.save(company);
            String key = (isNew) ? "company.added" : "company.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:companyform?id=" + company.getId();
            }
        }

        return success;
    }
}

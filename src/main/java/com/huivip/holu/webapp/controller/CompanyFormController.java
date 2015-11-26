package com.huivip.holu.webapp.controller;

import com.huivip.holu.model.Company;
import com.huivip.holu.service.CompanyManager;
import com.huivip.holu.util.cache.Cache2kProvider;
import org.apache.commons.lang.StringUtils;
import org.cache2k.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/companyform*")
public class CompanyFormController extends BaseFormController {
    private CompanyManager companyManager = null;
    Cache<String,Company> cache= Cache2kProvider.getinstance().getCache(Company.class.getName());
    Cache<String,List<Company>> listCache=Cache2kProvider.getinstance().getCache(ArrayList.class.getName());

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
            Company company=cache.peek(id);
            if(company==null){
                company=companyManager.get(new Long(id));
                cache.put(id,company);
            }
            return company;
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
            cache.remove(company.getId().toString());
            companyManager.remove(company.getId());
            saveMessage(request, getText("company.deleted", locale));
        } else {
            Company company1=companyManager.save(company);
            cache.put(company1.getId().toString(),company1);
            String key = (isNew) ? "company.added" : "company.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:companyform?id=" + company.getId();
            }
        }
        listCache.remove(Company.LIST_CACHE_KEY);
        return success;
    }
}

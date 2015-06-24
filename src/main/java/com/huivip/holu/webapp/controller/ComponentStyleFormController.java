package com.huivip.holu.webapp.controller;

import com.huivip.holu.model.Company;
import com.huivip.holu.model.ComponentStyle;
import com.huivip.holu.service.CompanyManager;
import com.huivip.holu.service.ComponentStyleManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/componentStyleform*")
public class ComponentStyleFormController extends BaseFormController {
    private ComponentStyleManager componentStyleManager = null;
    @Autowired
    private CompanyManager companyManager;

    @Autowired
    public void setComponentStyleManager(ComponentStyleManager componentStyleManager) {
        this.componentStyleManager = componentStyleManager;
    }

    public ComponentStyleFormController() {
        setCancelView("redirect:componentStyles");
        setSuccessView("redirect:componentStyles");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected ComponentStyle showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return componentStyleManager.get(new Long(id));
        }

        return new ComponentStyle();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(ComponentStyle componentStyle, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(componentStyle, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "componentStyleform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (componentStyle.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            componentStyleManager.remove(componentStyle.getId());
            saveMessage(request, getText("componentStyle.deleted", locale));
        } else {
            componentStyleManager.save(componentStyle);
            String key = (isNew) ? "componentStyle.added" : "componentStyle.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:componentStyleform?id=" + componentStyle.getId();
            }
        }

        return success;
    }
    @ModelAttribute("companyList")
    public List<Company> companyList(){
        return companyManager.getAll();
    }
}

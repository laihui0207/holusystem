package com.huivip.holu.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.huivip.holu.service.SummaryTotalManager;
import com.huivip.holu.model.SummaryTotal;
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
@RequestMapping("/summaryTotalform*")
public class SummaryTotalFormController extends BaseFormController {
    private SummaryTotalManager summaryTotalManager = null;

    @Autowired
    public void setSummaryTotalManager(SummaryTotalManager summaryTotalManager) {
        this.summaryTotalManager = summaryTotalManager;
    }

    public SummaryTotalFormController() {
        setCancelView("redirect:summaryTotals");
        setSuccessView("redirect:summaryTotals");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected SummaryTotal showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return summaryTotalManager.get(new Long(id));
        }

        return new SummaryTotal();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(SummaryTotal summaryTotal, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(summaryTotal, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "summaryTotalform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (summaryTotal.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            summaryTotalManager.remove(summaryTotal.getId());
            saveMessage(request, getText("summaryTotal.deleted", locale));
        } else {
            summaryTotalManager.save(summaryTotal);
            String key = (isNew) ? "summaryTotal.added" : "summaryTotal.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:summaryTotalform?id=" + summaryTotal.getId();
            }
        }

        return success;
    }
}

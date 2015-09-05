package com.huivip.holu.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.huivip.holu.service.SummaryManager;
import com.huivip.holu.model.Summary;
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
@RequestMapping("/summaryform*")
public class SummaryFormController extends BaseFormController {
    private SummaryManager summaryManager = null;

    @Autowired
    public void setSummaryManager(SummaryManager summaryManager) {
        this.summaryManager = summaryManager;
    }

    public SummaryFormController() {
        setCancelView("redirect:summaries");
        setSuccessView("redirect:summaries");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Summary showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return summaryManager.get(new Long(id));
        }

        return new Summary();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Summary summary, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(summary, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "summaryform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (summary.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            summaryManager.remove(summary.getId());
            saveMessage(request, getText("summary.deleted", locale));
        } else {
            summaryManager.save(summary);
            String key = (isNew) ? "summary.added" : "summary.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:summaryform?id=" + summary.getId();
            }
        }

        return success;
    }
}

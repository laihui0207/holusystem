package com.huivip.holu.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.huivip.holu.service.NewsManager;
import com.huivip.holu.model.News;
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
@RequestMapping("/newsform*")
public class NewsFormController extends BaseFormController {
    private NewsManager newsManager = null;

    @Autowired
    public void setNewsManager(NewsManager newsManager) {
        this.newsManager = newsManager;
    }

    public NewsFormController() {
        setCancelView("redirect:newss");
        setSuccessView("redirect:newss");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected News showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return newsManager.get(new Long(id));
        }

        return new News();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(News news, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(news, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "newsform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (news.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            newsManager.remove(news.getId());
            saveMessage(request, getText("news.deleted", locale));
        } else {
            newsManager.save(news);
            String key = (isNew) ? "news.added" : "news.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:newsform?id=" + news.getId();
            }
        }

        return success;
    }
}

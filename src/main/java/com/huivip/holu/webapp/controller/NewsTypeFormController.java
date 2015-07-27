package com.huivip.holu.webapp.controller;

import com.huivip.holu.model.NewsType;
import com.huivip.holu.model.User;
import com.huivip.holu.service.NewsTypeManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/newsTypeform*")
public class NewsTypeFormController extends BaseFormController {
    private NewsTypeManager newsTypeManager = null;

    @Autowired
    public void setNewsTypeManager(NewsTypeManager newsTypeManager) {
        this.newsTypeManager = newsTypeManager;
    }

    public NewsTypeFormController() {
        setCancelView("redirect:newsTypes");
        setSuccessView("redirect:newsTypes");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected NewsType showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return newsTypeManager.get(new Long(id));
        }

        return new NewsType();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(NewsType newsType, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(newsType, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "newsTypeform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (newsType.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            newsTypeManager.remove(newsType.getId());
            saveMessage(request, getText("newsType.deleted", locale));
        } else {
            final User cleanUser = getUserManager().getUserByLoginCode(
                    request.getRemoteUser());
            newsType.setCreater(cleanUser);
            newsType.setUpdater(cleanUser);
            newsTypeManager.save(newsType);
            String key = (isNew) ? "newsType.added" : "newsType.updated";
            saveMessage(request, getText(key, locale));
/*
            if (!isNew) {
                success = "redirect:newsTypeform?id=" + newsType.getId();
            }*/
        }

        return success;
    }
}

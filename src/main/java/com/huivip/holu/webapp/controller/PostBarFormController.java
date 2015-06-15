package com.huivip.holu.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.huivip.holu.service.PostBarManager;
import com.huivip.holu.model.PostBar;
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
@RequestMapping("/postBarform*")
public class PostBarFormController extends BaseFormController {
    private PostBarManager postBarManager = null;

    @Autowired
    public void setPostBarManager(PostBarManager postBarManager) {
        this.postBarManager = postBarManager;
    }

    public PostBarFormController() {
        setCancelView("redirect:postBars");
        setSuccessView("redirect:postBars");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected PostBar showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return postBarManager.get(new Long(id));
        }

        return new PostBar();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(PostBar postBar, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(postBar, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "postBarform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (postBar.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            postBarManager.remove(postBar.getId());
            saveMessage(request, getText("postBar.deleted", locale));
        } else {
            postBarManager.save(postBar);
            String key = (isNew) ? "postBar.added" : "postBar.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:postBarform?id=" + postBar.getId();
            }
        }

        return success;
    }
}

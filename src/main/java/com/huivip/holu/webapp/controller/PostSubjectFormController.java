package com.huivip.holu.webapp.controller;

import com.huivip.holu.model.PostSubject;
import com.huivip.holu.model.User;
import com.huivip.holu.service.PostSubjectManager;
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
@RequestMapping("/postSubjectform*")
public class PostSubjectFormController extends BaseFormController {
    private PostSubjectManager postSubjectManager = null;

    @Autowired
    public void setPostSubjectManager(PostSubjectManager postSubjectManager) {
        this.postSubjectManager = postSubjectManager;
    }

    public PostSubjectFormController() {
        setCancelView("redirect:postSubjects");
        setSuccessView("redirect:postSubjects");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected PostSubject showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return postSubjectManager.get(new Long(id));
        }

        return new PostSubject();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(PostSubject postSubject, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(postSubject, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "postSubjectform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (postSubject.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            postSubjectManager.remove(postSubject.getId());
            saveMessage(request, getText("postSubject.deleted", locale));
        } else {
            final User cleanUser = getUserManager().getUserByUsername(
                    request.getRemoteUser());
            postSubject.setCreater(cleanUser);
            postSubject.setUpdater(cleanUser);
            postSubjectManager.save(postSubject);
            String key = (isNew) ? "postSubject.added" : "postSubject.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:postSubjectform?id=" + postSubject.getId();
            }
        }

        return success;
    }
}

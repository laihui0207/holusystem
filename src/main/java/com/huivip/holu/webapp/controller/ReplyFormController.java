package com.huivip.holu.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.huivip.holu.service.ReplyManager;
import com.huivip.holu.model.Reply;
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
@RequestMapping("/replyform*")
public class ReplyFormController extends BaseFormController {
    private ReplyManager replyManager = null;

    @Autowired
    public void setReplyManager(ReplyManager replyManager) {
        this.replyManager = replyManager;
    }

    public ReplyFormController() {
        setCancelView("redirect:replies");
        setSuccessView("redirect:replies");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Reply showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return replyManager.get(new Long(id));
        }

        return new Reply();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Reply reply, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(reply, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "replyform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (reply.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            replyManager.remove(reply.getId());
            saveMessage(request, getText("reply.deleted", locale));
        } else {
            replyManager.save(reply);
            String key = (isNew) ? "reply.added" : "reply.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:replyform?id=" + reply.getId();
            }
        }

        return success;
    }
}

package com.huivip.holu.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.huivip.holu.service.MessageManager;
import com.huivip.holu.model.Message;
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
@RequestMapping("/messageform*")
public class MessageFormController extends BaseFormController {
    private MessageManager messageManager = null;

    @Autowired
    public void setMessageManager(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    public MessageFormController() {
        setCancelView("redirect:messages");
        setSuccessView("redirect:messages");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Message showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return messageManager.get(new Long(id));
        }

        return new Message();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Message message, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(message, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "messageform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (message.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            messageManager.remove(message.getId());
            saveMessage(request, getText("message.deleted", locale));
        } else {
            messageManager.save(message);
            String key = (isNew) ? "message.added" : "message.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:messageform?id=" + message.getId();
            }
        }

        return success;
    }
}

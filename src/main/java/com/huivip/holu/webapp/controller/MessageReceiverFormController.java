package com.huivip.holu.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.huivip.holu.service.MessageReceiverManager;
import com.huivip.holu.model.MessageReceiver;
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
@RequestMapping("/messageReceiverform*")
public class MessageReceiverFormController extends BaseFormController {
    private MessageReceiverManager messageReceiverManager = null;

    @Autowired
    public void setMessageReceiverManager(MessageReceiverManager messageReceiverManager) {
        this.messageReceiverManager = messageReceiverManager;
    }

    public MessageReceiverFormController() {
        setCancelView("redirect:messageReceivers");
        setSuccessView("redirect:messageReceivers");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected MessageReceiver showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return messageReceiverManager.get(new Long(id));
        }

        return new MessageReceiver();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(MessageReceiver messageReceiver, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(messageReceiver, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "messageReceiverform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (messageReceiver.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            messageReceiverManager.remove(messageReceiver.getId());
            saveMessage(request, getText("messageReceiver.deleted", locale));
        } else {
            messageReceiverManager.save(messageReceiver);
            String key = (isNew) ? "messageReceiver.added" : "messageReceiver.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:messageReceiverform?id=" + messageReceiver.getId();
            }
        }

        return success;
    }
}

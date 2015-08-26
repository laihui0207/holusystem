package com.huivip.holu.webapp.controller;

import com.huivip.holu.model.Message;
import com.huivip.holu.model.MessageReply;
import com.huivip.holu.model.User;
import com.huivip.holu.service.MessageManager;
import com.huivip.holu.service.MessageReplyManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;

@Controller
@RequestMapping("/messageReplyform*")
public class MessageReplyFormController extends BaseFormController {
    private MessageReplyManager messageReplyManager = null;
    @Autowired
    private MessageManager messageManager;

    @Autowired
    public void setMessageReplyManager(MessageReplyManager messageReplyManager) {
        this.messageReplyManager = messageReplyManager;
    }

    public MessageReplyFormController() {
        setCancelView("redirect:messageReplies");
        setSuccessView("redirect:messageReplies");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected MessageReply showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        if (!StringUtils.isBlank(id)) {
            return messageReplyManager.get(new Long(id));
        }

        return new MessageReply();
    }

    @RequestMapping(method = RequestMethod.GET, value = "{messageId}")
    public String showFrom(@PathVariable(value = "messageId") String messageId, HttpServletRequest request,Model model) {
        MessageReply reply = new MessageReply();
        Message message = messageManager.get(Long.parseLong(messageId));
        if (null == message) {
            return "redirect:messageReplies";
        }
        model.addAttribute("message",message);
        reply.setMessage(message);
        String id=request.getParameter("id");
        if(!StringUtils.isBlank(id)){
            reply=messageReplyManager.get(Long.parseLong(id));
        }
        model.addAttribute("messageReply",reply);
        return "messageReplyform";
    }
    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(MessageReply messageReply, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return "redirect:messageReplies/"+messageReply.getMessage().getId();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(messageReply, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "messageReplyform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (messageReply.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            messageReplyManager.remove(messageReply.getId());
            saveMessage(request, getText("messageReply.deleted", locale));
        } else {
            User currentUser=getUserManager().getUserByLoginCode(request.getRemoteUser());
            messageReply.setCreateTime(new Timestamp(new Date().getTime()));
            messageReply.setReplyUser(currentUser);
            messageReplyManager.save(messageReply);
            String key = (isNew) ? "messageReply.added" : "messageReply.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:messageReplyform?id=" + messageReply.getId();
            }
        }

        return "redirect:messageReplies/"+messageReply.getMessage().getId();
    }
}

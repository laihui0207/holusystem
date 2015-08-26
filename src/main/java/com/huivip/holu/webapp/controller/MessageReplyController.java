package com.huivip.holu.webapp.controller;

import com.huivip.holu.dao.SearchException;
import com.huivip.holu.model.Message;
import com.huivip.holu.model.MessageReply;
import com.huivip.holu.service.MessageManager;
import com.huivip.holu.service.MessageReplyManager;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import com.huivip.holu.webapp.helper.PaginateListFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/messageReplies*")
public class MessageReplyController {
    private MessageReplyManager messageReplyManager;
    @Autowired
    PaginateListFactory paginateListFactory;
    @Autowired
    MessageManager messageManager;

    @Autowired
    public void setMessageReplyManager(MessageReplyManager messageReplyManager) {
        this.messageReplyManager = messageReplyManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(messageReplyManager.search(query, MessageReply.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(messageReplyManager.getAll());
        }
        return model;
    }
    @RequestMapping(value = "{messageId}",method = RequestMethod.GET)
    public ModelAndView messageReplies(@PathVariable(value = "messageId") String messageId,HttpServletRequest request)
            throws Exception {
        ModelAndView model = new ModelAndView("messageReplies");
        ExtendedPaginatedList list=paginateListFactory.getPaginatedListFromRequest(request);
        try {
            Message message=messageManager.get(Long.parseLong(messageId));
            messageReplyManager.replyListOfMessage(messageId,list);
            model.addObject("messageReplyList",list);
            model.addObject("message",message);
        } catch (SearchException se) {
            model.addObject("searchError", se.getMessage());
            model.addObject(messageReplyManager.getAll());
        }
        return model;
    }
}

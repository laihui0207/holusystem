package com.huivip.holu.webapp.controller;

import com.huivip.holu.dao.SearchException;
import com.huivip.holu.model.Message;
import com.huivip.holu.model.User;
import com.huivip.holu.service.MessageManager;
import com.huivip.holu.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/messages*")
public class MessageController {
    private MessageManager messageManager;
    @Autowired
    private UserManager userManager;

    @Autowired
    public void setMessageManager(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query,HttpServletRequest request)
    throws Exception {
        final User cleanUser =userManager.getUserByUsername(
                request.getRemoteUser());
        Model model = new ExtendedModelMap();
        try {
            if(null!=query && query.length()>0) {
                model.addAttribute(messageManager.search(query, Message.class));
            }
            else {
                model.addAttribute(messageManager.messageByOwner(cleanUser));
            }
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(messageManager.messageByOwner(cleanUser));
        }
        return model;
    }
}

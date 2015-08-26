package com.huivip.holu.webapp.controller;

import com.huivip.holu.dao.SearchException;
import com.huivip.holu.service.MessageReceiverManager;
import com.huivip.holu.model.MessageReceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/messageReceivers*")
public class MessageReceiverController {
    private MessageReceiverManager messageReceiverManager;

    @Autowired
    public void setMessageReceiverManager(MessageReceiverManager messageReceiverManager) {
        this.messageReceiverManager = messageReceiverManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(messageReceiverManager.search(query, MessageReceiver.class));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(messageReceiverManager.getAll());
        }
        return model;
    }
}

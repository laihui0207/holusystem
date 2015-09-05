package com.huivip.holu.webapp.controller;

import com.huivip.holu.Constants;
import com.huivip.holu.dao.SearchException;
import com.huivip.holu.model.Message;
import com.huivip.holu.model.MessageReceiver;
import com.huivip.holu.model.User;
import com.huivip.holu.service.MessageManager;
import com.huivip.holu.service.MessageReceiverManager;
import com.huivip.holu.service.UserManager;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import com.huivip.holu.webapp.helper.PaginateListFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/messages*")
public class MessageController {
    private MessageManager messageManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    PaginateListFactory paginateListFactory;
    @Autowired
    MessageReceiverManager messageReceiverManager;

    @Autowired
    public void setMessageManager(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query,HttpServletRequest request)
    throws Exception {
        final User cleanUser =userManager.getUserByLoginCode(
                request.getRemoteUser());
        ExtendedPaginatedList list=paginateListFactory.getPaginatedListFromRequest(request);
        Model model = new ExtendedModelMap();
        try {
            if(request.isUserInRole(Constants.ADMIN_ROLE)){
                messageReceiverManager.search(query, MessageReceiver.class, list);
                model.addAttribute("messageList",list);
            }
            else {
                List<MessageReceiver> resultList=new ArrayList<>();
                if(query==null || query.equals("")){
                    //messageManager.messageByOwner(cleanUser,list);
                    messageReceiverManager.listMyMessage(cleanUser.getId().toString(),"all" , list);
                    model.addAttribute("messageList",list);
                }
                else {
                    List<MessageReceiver> messageList = messageReceiverManager.search(query, Message.class);
                    for (MessageReceiver message : messageList) {
                        if (message.getReceiver().getUsername().equals(cleanUser.getUsername())) {
                            resultList.add(message);
                        }
                    }
                    model.addAttribute("messageList",resultList);
                }
            }
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            messageManager.messageByOwner(cleanUser,list);
            model.addAttribute("messageList",list);
        }
        return model;
    }
}

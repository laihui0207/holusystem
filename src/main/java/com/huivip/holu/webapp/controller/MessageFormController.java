package com.huivip.holu.webapp.controller;

import com.huivip.holu.model.CustomGroup;
import com.huivip.holu.model.Message;
import com.huivip.holu.model.MessageReceiver;
import com.huivip.holu.model.User;
import com.huivip.holu.service.CustomGroupManager;
import com.huivip.holu.service.MessageManager;
import com.huivip.holu.service.MessageReceiverManager;
import com.huivip.holu.service.UserManager;
import com.huivip.holu.util.cache.Cache2kProvider;
import org.apache.commons.lang.StringUtils;
import org.cache2k.Cache;
import org.cache2k.CacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("/messageform*")
public class MessageFormController extends BaseFormController {
    Cache<String,Message> cache= null;
    private MessageManager messageManager = null;
    @Autowired
    private UserManager userManager;
    @Autowired
    CustomGroupManager customGroupManager;
    @Autowired
    MessageReceiverManager messageReceiverManager;

    @Autowired
    public void setMessageManager(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    public MessageFormController() {
        setCancelView("redirect:messages");
        setSuccessView("redirect:messages");
        cache= Cache2kProvider.getinstance().setCache(Message.class,
                CacheBuilder.newCache(String.class,Message.class).build());
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Message showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            Message message=cache.peek(id);
            if(message==null){
                message=messageManager.get(new Long(id));
                cache.put(id,message);
            }
            return message;
        }

        return new Message();
    }
    @RequestMapping(method = RequestMethod.GET,value = "{id}/Send")
    public ModelAndView sendMessageForm(@PathVariable("id") String messageId){
        Message message=cache.peek(messageId);
        if(message==null){
            message=messageManager.get(Long.parseLong(messageId));
            cache.put(messageId,message);
        }
        if(message.getStatus()==2){
            message.setStatus(3);
        }
       Message message1= messageManager.save(message);
        cache.put(message1.getId().toString(),message1);
        removeCacheByKeyPrefix(Message.LIST_NEWS_CACHE_KEY,Message.LIST_CACHE);
        ModelAndView view=new ModelAndView("messagesend");
        view.addObject("message",message);
        return view;
    }

    @RequestMapping(method = RequestMethod.POST,value = "{id}/Send")
    public String sendMessage(@PathVariable("id") String messageId,HttpServletRequest request){
        if (request.getParameter("cancel") != null) {
            return "redirect:/messages";
        }
        Message message=cache.peek(messageId);
        if(message==null){
           message=messageManager.get(Long.parseLong(messageId));
            cache.put(messageId,message);
        }

        String[] receiveUsers=request.getParameterValues("receiveUsers");
        String[] receiveUserGroups=request.getParameterValues("receiveGroups");

        Set<User> receiveUserList=new HashSet<>();

        if(null!=receiveUsers){
            for(String userID:receiveUsers){
                receiveUserList.add(userManager.get(Long.parseLong(userID)));
                message.getReceiveUsers().add(userManager.get(Long.parseLong(userID)));
            }
        }

        if(null!=receiveUserGroups){
            for(String groupId:receiveUserGroups){
                receiveUserList.addAll(customGroupManager.get(Long.parseLong(groupId)).getMembers());
                message.getReceiveGroups().add(customGroupManager.get(Long.parseLong(groupId)));
            }
        }
        message.setStatus(1);
        Message message1=messageManager.save(message);

        MessageReceiver receiver=new MessageReceiver();
        receiver.setMessage(message);
        receiver.setCreateTime(new Timestamp(new Date().getTime()));

        for(User user:receiveUserList){
            receiver.setId(null);
            receiver.setReceiver(user);
            receiver.setStatus(0);
            messageReceiverManager.save(receiver);
        }
        cache.put(message1.getId().toString(),message1);
        removeCacheByKeyPrefix(Message.LIST_NEWS_CACHE_KEY,Message.LIST_CACHE);
        saveMessage(request,"Send Success");
        return "redirect:/messages";
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
            messageReceiverManager.deleteReceiverOfMessage(message.getId().toString());
            cache.remove(message.getId().toString());
            messageManager.remove(message.getId());
            removeCacheByKeyPrefix(Message.LIST_NEWS_CACHE_KEY,Message.LIST_CACHE);
            saveMessage(request, getText("message.deleted", locale));
        } else {
            final User cleanUser = getUserManager().getUserByLoginCode(
                    request.getRemoteUser());
            message.setCreater(cleanUser);
            message.setOwner(cleanUser);
            message.setUpdater(cleanUser);
            message.setOwner(cleanUser);
            message.setStatus(0);
            Message newMessage=messageManager.save(message);
            MessageReceiver receiver=new MessageReceiver();
            receiver.setStatus(1);
            receiver.setMessage(newMessage);
            receiver.setReceiver(cleanUser);
            receiver.setCreateTime(new Timestamp(new Date().getTime()));
            messageReceiverManager.save(receiver);
            String key = (isNew) ? "message.added" : "message.updated";
            saveMessage(request, getText(key, locale));

            /*if (!isNew) {
                success = "redirect:messageform?id=" + message.getId();
            }*/
            cache.put(newMessage.getId().toString(),newMessage);
            removeCacheByKeyPrefix(Message.LIST_NEWS_CACHE_KEY,Message.LIST_CACHE);
        }

        return success;
    }
    @ModelAttribute("userList")
    public List<User> userList(){
        return userManager.getAll();
    }
    @ModelAttribute("userGroupList")
    public List<CustomGroup> userGroupList(){
        return customGroupManager.getAll();
    }
}

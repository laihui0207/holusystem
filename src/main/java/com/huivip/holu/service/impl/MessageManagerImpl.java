package com.huivip.holu.service.impl;

import com.huivip.holu.dao.CustomGroupDao;
import com.huivip.holu.dao.MessageDao;
import com.huivip.holu.dao.UserDao;
import com.huivip.holu.model.CustomGroup;
import com.huivip.holu.model.Message;
import com.huivip.holu.model.User;
import com.huivip.holu.service.MessageManager;

import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;

@Service("messageManager")
@WebService(serviceName = "MessageService", endpointInterface = "com.huivip.holu.service.MessageManager")
public class MessageManagerImpl extends GenericManagerImpl<Message, Long> implements MessageManager {
    MessageDao messageDao;
    @Autowired
    UserDao userDao;
    @Autowired
    CustomGroupDao customGroupDao;

    @Autowired
    public MessageManagerImpl(MessageDao messageDao) {
        super(messageDao);
        this.messageDao = messageDao;
    }

    @Override
    public List<Message> messageByOwner(User user,ExtendedPaginatedList list) {
        return messageDao.messageByOwner(user,list);
    }

    @Override
    public List<Message> myMessage(String userId) {
        User user=userDao.get(Long.parseLong(userId));
        return messageByOwner(user,null);
    }

    @Override
    public int newMessageCount(String userId) {
        return messageDao.newMessageCount(userId);
    }

    @Override
    public Message getMessage(String id) {
        return messageDao.get(Long.parseLong(id));
    }

    @Override
    public Message readMessage(String id) {
        return messageDao.updateMessageStatus(id,"3");
    }

    @Override
    public Message saveMessage(String title, String content,String userId, String messageId) {
        User user=userDao.get(Long.parseLong(userId));
        Message message=new Message();
        if(messageId!=null && messageId.length()>0 && !messageId.equalsIgnoreCase("undefined")){
            message=messageDao.get(Long.parseLong(messageId));
        }
        message.setTitle(title);
        message.setContent(content);
        message.setCreater(user);
        message.setUpdater(user);
        message.setUpdateTime(new Timestamp(new Date().getTime()));
        message.setOwner(user);
        messageDao.save(message);
        return message;
    }

    @Override
    public Message sendMessage(String messageId, String users, String groups, String userId) {
        User user=userDao.get(Long.parseLong(userId));
        Message message=messageDao.get(Long.parseLong(messageId));
        List<User> receiverList=new ArrayList<>();
        if(null!=users && users.length()>0 && !users.equalsIgnoreCase("undefined")){
            if(message.getReceiveUsers()!=null){
                message.getReceiveUsers().clear();
            }
            String[] usersArray=users.split(";");
            for(String id:usersArray){
                User user1=userDao.get(Long.parseLong(id));
                if(!receiverList.contains(user1)){
                    receiverList.add(user1);
                }
                message.getReceiveUsers().add(user1);
            }
        }
        if(null!=groups && groups.length()>0 && !groups.equalsIgnoreCase("undefined")){
            if(message.getReceiveGroups()!=null){
                message.getReceiveGroups().clear();
            }
            String[] groupArray=groups.split(";");
            for(String id:groupArray){
                CustomGroup ug=customGroupDao.get(Long.parseLong(id));
                message.getReceiveGroups().add(ug);
                for(User u:ug.getMembers()){
                    if(!receiverList.contains(u)){
                        receiverList.add(u);
                    }
                }
            }
        }
        message.setStatus(1);
        messageDao.save(message);

        Message copyMessage=new Message();
        copyMessage.setTitle(message.getTitle());
        copyMessage.setContent(message.getContent());
        copyMessage.setCreater(message.getCreater());
        copyMessage.setStatus(2);

        copyMessage.setSender(user);

        for(User u:receiverList){
            copyMessage.setId(null);
            copyMessage.setOwner(u);
            messageDao.save(copyMessage);
        }
        return message;
    }

    @Override
    public void deleteMessage(String messageId) {
        messageDao.remove(Long.parseLong(messageId));
    }
}
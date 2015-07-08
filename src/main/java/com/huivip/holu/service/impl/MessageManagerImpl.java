package com.huivip.holu.service.impl;

import com.huivip.holu.dao.MessageDao;
import com.huivip.holu.dao.UserDao;
import com.huivip.holu.model.Message;
import com.huivip.holu.model.User;
import com.huivip.holu.service.MessageManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.ws.rs.FormParam;

@Service("messageManager")
@WebService(serviceName = "MessageService", endpointInterface = "com.huivip.holu.service.MessageManager")
public class MessageManagerImpl extends GenericManagerImpl<Message, Long> implements MessageManager {
    MessageDao messageDao;
    @Autowired
    UserDao userDao;

    @Autowired
    public MessageManagerImpl(MessageDao messageDao) {
        super(messageDao);
        this.messageDao = messageDao;
    }

    @Override
    public List<Message> messageByOwner(User user) {
        return messageDao.messageByOwner(user);
    }

    @Override
    public List<Message> myMessage(String userId) {
        User user=userDao.get(Long.parseLong(userId));
        return messageByOwner(user);
    }

    @Override
    public Message getMessage(String id) {
        return messageDao.get(Long.parseLong(id));
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
    public void deleteMessage(String messageId) {
        messageDao.remove(Long.parseLong(messageId));
    }
}
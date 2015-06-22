package com.huivip.holu.service.impl;

import com.huivip.holu.dao.MessageDao;
import com.huivip.holu.model.Message;
import com.huivip.holu.model.User;
import com.huivip.holu.service.MessageManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("messageManager")
@WebService(serviceName = "MessageService", endpointInterface = "com.huivip.holu.service.MessageManager")
public class MessageManagerImpl extends GenericManagerImpl<Message, Long> implements MessageManager {
    MessageDao messageDao;

    @Autowired
    public MessageManagerImpl(MessageDao messageDao) {
        super(messageDao);
        this.messageDao = messageDao;
    }

    @Override
    public List<Message> messageByOwner(User user) {
        return messageDao.messageByOwner(user);
    }
}
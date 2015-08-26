package com.huivip.holu.service.impl;

import com.huivip.holu.dao.MessageReceiverDao;
import com.huivip.holu.model.MessageReceiver;
import com.huivip.holu.service.MessageReceiverManager;

import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("messageReceiverManager")
@WebService(serviceName = "MessageReceiverService", endpointInterface = "com.huivip.holu.service.MessageReceiverManager")
public class MessageReceiverManagerImpl extends GenericManagerImpl<MessageReceiver, Long> implements MessageReceiverManager {
    MessageReceiverDao messageReceiverDao;

    @Autowired
    public MessageReceiverManagerImpl(MessageReceiverDao messageReceiverDao) {
        super(messageReceiverDao);
        this.messageReceiverDao = messageReceiverDao;
    }

    @Override
    public List<MessageReceiver> listMyMessage(String userId, ExtendedPaginatedList list) {
        return messageReceiverDao.listMyMessage(userId,list);
    }
}
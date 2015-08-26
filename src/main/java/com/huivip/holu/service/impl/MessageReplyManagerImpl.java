package com.huivip.holu.service.impl;

import com.huivip.holu.dao.MessageReplyDao;
import com.huivip.holu.model.MessageReply;
import com.huivip.holu.service.MessageReplyManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("messageReplyManager")
@WebService(serviceName = "MessageReplyService", endpointInterface = "com.huivip.holu.service.MessageReplyManager")
public class MessageReplyManagerImpl extends GenericManagerImpl<MessageReply, Long> implements MessageReplyManager {
    MessageReplyDao messageReplyDao;

    @Autowired
    public MessageReplyManagerImpl(MessageReplyDao messageReplyDao) {
        super(messageReplyDao);
        this.messageReplyDao = messageReplyDao;
    }

    @Override
    public List<MessageReply> replyListOfMessage(String messageID, ExtendedPaginatedList list) {
        return messageReplyDao.replyListOfMessage(messageID,list);
    }
}
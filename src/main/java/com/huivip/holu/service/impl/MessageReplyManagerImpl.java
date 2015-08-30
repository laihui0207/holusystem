package com.huivip.holu.service.impl;

import com.huivip.holu.dao.MessageReplyDao;
import com.huivip.holu.model.Message;
import com.huivip.holu.model.MessageReply;
import com.huivip.holu.model.User;
import com.huivip.holu.service.MessageManager;
import com.huivip.holu.service.MessageReplyManager;
import com.huivip.holu.service.UserManager;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import javax.ws.rs.FormParam;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service("messageReplyManager")
@WebService(serviceName = "MessageReplyService", endpointInterface = "com.huivip.holu.service.MessageReplyManager")
public class MessageReplyManagerImpl extends GenericManagerImpl<MessageReply, Long> implements MessageReplyManager {
    MessageReplyDao messageReplyDao;
    @Autowired
    MessageManager messageManager;
    @Autowired
    UserManager userManager;

    @Autowired
    public MessageReplyManagerImpl(MessageReplyDao messageReplyDao) {
        super(messageReplyDao);
        this.messageReplyDao = messageReplyDao;
    }

    @Override
    public List<MessageReply> replyListOfMessage(String messageID, ExtendedPaginatedList list) {
        return messageReplyDao.replyListOfMessage(messageID,list);
    }

    @Override
    public Message replyMessage( String content,  String userId,  String messageId) {
        Message message=messageManager.get(Long.parseLong(messageId));
        if(message==null) return null;
        MessageReply reply=new MessageReply();
        reply.setMessage(message);
        User user=userManager.get(Long.parseLong(userId));
        reply.setReplyUser(user);
        reply.setCreateTime(new Timestamp(new Date().getTime()));
        reply.setContent(content);
        MessageReply newReply=messageReplyDao.save(reply);
        return newReply.getMessage();
    }
}
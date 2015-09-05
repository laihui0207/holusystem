package com.huivip.holu.service;

import com.huivip.holu.model.MessageReceiver;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface MessageReceiverManager extends GenericManager<MessageReceiver, Long> {

    List<MessageReceiver> listMyMessage(String userId, String messageType, ExtendedPaginatedList list);
    void deleteReceiverOfMessage(String messageId);
    void messageRead(String messsageId,String userId);
    int newMessage(String userId);
    
}
package com.huivip.holu.service;

import com.huivip.holu.model.MessageReceiver;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface MessageReceiverManager extends GenericManager<MessageReceiver, Long> {

    List<MessageReceiver> listMyMessage(String userId, ExtendedPaginatedList list);
    
}
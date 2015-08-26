package com.huivip.holu.service;

import com.huivip.holu.model.MessageReply;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface MessageReplyManager extends GenericManager<MessageReply, Long> {

    List<MessageReply> replyListOfMessage(String messageID,ExtendedPaginatedList list);
}
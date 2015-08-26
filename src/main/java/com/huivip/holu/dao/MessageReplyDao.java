package com.huivip.holu.dao;

import com.huivip.holu.model.MessageReply;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;

import java.util.List;

/**
 * An interface that provides a data management interface to the MessageReply table.
 */
public interface MessageReplyDao extends GenericDao<MessageReply, Long> {
    List<MessageReply> replyListOfMessage(String messageID,ExtendedPaginatedList list);

}
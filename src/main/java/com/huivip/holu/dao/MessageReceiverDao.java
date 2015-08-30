package com.huivip.holu.dao;

import com.huivip.holu.model.MessageReceiver;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;

import java.util.List;

/**
 * An interface that provides a data management interface to the MessageReceiver table.
 */
public interface MessageReceiverDao extends GenericDao<MessageReceiver, Long> {

    List<MessageReceiver> listMyMessage(String userId, ExtendedPaginatedList list);
    void deleteReceiverOfMessage(String messsageId);

}
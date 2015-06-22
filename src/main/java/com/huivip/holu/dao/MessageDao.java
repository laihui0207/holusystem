package com.huivip.holu.dao;

import com.huivip.holu.model.Message;
import com.huivip.holu.model.User;

import java.util.List;

/**
 * An interface that provides a data management interface to the Message table.
 */
public interface MessageDao extends GenericDao<Message, Long> {
    List<Message> messageByOwner(User user);

}
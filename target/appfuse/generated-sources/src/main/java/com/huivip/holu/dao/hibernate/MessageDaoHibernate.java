package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.Message;
import com.huivip.holu.dao.MessageDao;
import com.huivip.holu.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("messageDao")
public class MessageDaoHibernate extends GenericDaoHibernate<Message, Long> implements MessageDao {

    public MessageDaoHibernate() {
        super(Message.class);
    }
}

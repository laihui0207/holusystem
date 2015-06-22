package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.MessageDao;
import com.huivip.holu.model.Message;
import com.huivip.holu.model.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("messageDao")
public class MessageDaoHibernate extends GenericDaoHibernate<Message, Long> implements MessageDao {

    public MessageDaoHibernate() {
        super(Message.class);
    }

    @Override
    public List<Message> messageByOwner(User user) {
        String queryString="From Message where creater.id="+user.getId()+" or owner.id="+user.getId();
        Query query=getSession().createQuery(queryString);

        return query.list();
    }
}

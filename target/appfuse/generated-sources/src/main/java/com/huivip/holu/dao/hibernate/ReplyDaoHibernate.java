package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.Reply;
import com.huivip.holu.dao.ReplyDao;
import com.huivip.holu.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("replyDao")
public class ReplyDaoHibernate extends GenericDaoHibernate<Reply, Long> implements ReplyDao {

    public ReplyDaoHibernate() {
        super(Reply.class);
    }
}

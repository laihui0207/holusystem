package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.ReplyDao;
import com.huivip.holu.model.Reply;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("replyDao")
public class ReplyDaoHibernate extends GenericDaoHibernate<Reply, Long> implements ReplyDao {

    public ReplyDaoHibernate() {
        super(Reply.class);
    }

    @Override
    public List<Reply> getReplyByPostbar(String postBarid) {
        String queryString="From Reply where postBar.id=:id order by replyTime";
        Query query=getSession().createQuery(queryString);
        query.setString("id",postBarid);
        return query.list();
    }
}

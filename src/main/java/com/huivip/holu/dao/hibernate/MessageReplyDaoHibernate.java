package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.MessageReplyDao;
import com.huivip.holu.model.MessageReply;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("messageReplyDao")
public class MessageReplyDaoHibernate extends GenericDaoHibernate<MessageReply, Long> implements MessageReplyDao {

    public MessageReplyDaoHibernate() {
        super(MessageReply.class);
    }

    @Override
    public List<MessageReply> replyListOfMessage(String messageID, ExtendedPaginatedList list) {
        String hql="From MessageReply where message.id="+messageID+ " order by createTime DESC";
        Query query=getSession().createQuery(hql);
        if(list!=null){
            List<MessageReply> totalList=query.list();
            query.setFirstResult(list.getFirstRecordIndex());
            query.setMaxResults(list.getPageSize());
            list.setTotalNumberOfRows(totalList.size());
        }
        List<MessageReply> dataList=query.list();
        if(list!=null){
            list.setList(dataList);
        }
        return dataList;
    }
}

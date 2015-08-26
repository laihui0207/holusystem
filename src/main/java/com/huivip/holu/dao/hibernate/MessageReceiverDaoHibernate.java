package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.MessageReceiverDao;
import com.huivip.holu.model.MessageReceiver;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("messageReceiverDao")
public class MessageReceiverDaoHibernate extends GenericDaoHibernate<MessageReceiver, Long> implements MessageReceiverDao {

    public MessageReceiverDaoHibernate() {
        super(MessageReceiver.class);
    }

    @Override
    public List<MessageReceiver> listMyMessage(String userId, ExtendedPaginatedList list) {
        String hql="From MessageReceiver where receiver.id="+userId;
        Query query=getSession().createQuery(hql);
        if(list!=null){
            List<MessageReceiver> totalData=query.list();
            query.setFirstResult(list.getFirstRecordIndex());
            query.setMaxResults(list.getPageSize());
            list.setTotalNumberOfRows(totalData.size());
        }
        List<MessageReceiver> dataList=query.list();
        if(list!=null){
            list.setList(dataList);
        }
        return dataList;
    }
}
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
    public List<MessageReceiver> listMyMessage(String userId, String messageType, ExtendedPaginatedList list) {
        String hql="";
        if(messageType==null || messageType.equalsIgnoreCase("all")) {
            hql = "From MessageReceiver mr where mr.receiver.id=" + userId + " order by status ASC, createTime DESC";
        }
        else if(messageType!=null && messageType.equalsIgnoreCase("UnRead")){
            hql = "From MessageReceiver mr where mr.receiver.id=" + userId + " and status=0 order by status ASC, createTime DESC";
        }
        else if(messageType!=null && messageType.equalsIgnoreCase("My")){
            hql = "From MessageReceiver mr where mr.receiver.id=" + userId + " and  mr.message.owner.id="+userId+" order by status ASC, createTime DESC";
        }
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

    @Override
    public void deleteReceiverOfMessage(String messageId) {
        String hql="delete From MessageReceiver where message.id="+messageId;
        Query query=getSession().createQuery(hql);
        query.executeUpdate();
    }

    @Override
    public void messageRead(String messsageId, String userId) {
        String hql="update MessageReceiver set status=1 where message.id="+messsageId+" and receiver.id="+userId;
        Query query=getSession().createQuery(hql);
        query.executeUpdate();
    }

    @Override
    public int newMessage(String userId) {
        String hql="From MessageReceiver mr where mr.receiver.id="+userId +"and status=0 order by status ASC, createTime DESC";
        Query query=getSession().createQuery(hql);
        List<MessageReceiver> datalist=query.list();
        if(null!=datalist && datalist.size()>0){
            return datalist.size();
        }
        return 0;
    }
}

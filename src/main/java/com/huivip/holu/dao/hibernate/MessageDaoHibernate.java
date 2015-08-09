package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.MessageDao;
import com.huivip.holu.model.Message;
import com.huivip.holu.model.User;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.displaytag.properties.SortOrderEnum;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("messageDao")
public class MessageDaoHibernate extends GenericDaoHibernate<Message, Long> implements MessageDao {

    public MessageDaoHibernate() {
        super(Message.class);
    }

    @Override
    public List<Message> messageByOwner(User user,ExtendedPaginatedList list) {
        Criteria criteria=getSession().createCriteria(Message.class);
        criteria.add(Restrictions.eq("owner.id", user.getId()));

        List<Message> dataList=criteria.list();
        int totalCount=dataList.size();

        if(list!=null){
            criteria.setFirstResult(list.getFirstRecordIndex());
            criteria.setMaxResults(list.getPageSize());
            if (list.getSortCriterion() != null) {
                if (list.getSortDirection().equals(SortOrderEnum.ASCENDING)) {
                    criteria.addOrder(Order.asc(list.getSortCriterion()));
                }
                if (list.getSortDirection().equals(SortOrderEnum.DESCENDING)) {
                    criteria.addOrder(Order.desc(list.getSortCriterion()));
                }
            }
            dataList=criteria.list();
            list.setList(dataList);
            list.setTotalNumberOfRows(totalCount);
        }
        return dataList;
    }

    @Override
    public int newMessageCount(String userId) {
        String hql="From Message where owner.id="+userId+" and status=2";
        Query query=getSession().createQuery(hql);
        List<Message> dataList=query.list();
        if(null!=dataList){
            return dataList.size();
        }
        return 0;
    }

    @Override
    public Message updateMessageStatus(String messageId, String status) {
        String hql="update Message set status="+status+" where id="+messageId;
        Query query=getSession().createQuery(hql);
        int updatecount=query.executeUpdate();
        if(updatecount>0){
            String getHql="From Message where id="+messageId;
            Query getQuery=getSession().createQuery(getHql);
            List<Message> list=getQuery.list();
            if(list!=null && list.size()>0){
                return list.get(0);
            }
        }
        return null;
    }
}

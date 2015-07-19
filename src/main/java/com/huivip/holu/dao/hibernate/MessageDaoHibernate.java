package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.MessageDao;
import com.huivip.holu.model.Message;
import com.huivip.holu.model.User;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.displaytag.properties.SortOrderEnum;
import org.hibernate.Criteria;
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
}

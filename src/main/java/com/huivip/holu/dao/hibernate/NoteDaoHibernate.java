package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.NoteDao;
import com.huivip.holu.model.Note;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.displaytag.properties.SortOrderEnum;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("noteDao")
public class NoteDaoHibernate extends GenericDaoHibernate<Note, Long> implements NoteDao {

    public NoteDaoHibernate() {
        super(Note.class);
    }

    @Override
    public List<Note> myNotes(String userid,ExtendedPaginatedList list) {
        Criteria criteria=getSession().createCriteria(Note.class);
        criteria.add(Restrictions.eq("receiver.id", Long.parseLong(userid)));
        List<Note> dataList=criteria.list();
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

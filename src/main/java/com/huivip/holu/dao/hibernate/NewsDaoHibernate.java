package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.NewsDao;
import com.huivip.holu.model.News;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("newsDao")
public class NewsDaoHibernate extends GenericDaoHibernate<News, Long> implements NewsDao {

    public NewsDaoHibernate() {
        super(News.class);
    }

    @Override
    public List<News> getNewsByType(String typeID) {
        String queryString="From News where newsType.id="+typeID;
        Query query=getSession().createQuery(queryString);
        return query.list();
    }

    @Override
    public List<News> getNewsPageable(ExtendedPaginatedList list) {
      return getAllPagable(list);
    }

    @Override
    public int getNewsCount() {
        Criteria criteria=getSession().createCriteria(News.class);
        criteria.setProjection(Projections.rowCount());
        Long result= (Long) criteria.list().get(0);
        Integer i = result != null ? result.intValue() : null;
        return i.intValue();
    }
}

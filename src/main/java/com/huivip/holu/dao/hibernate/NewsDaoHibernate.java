package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.NewsDao;
import com.huivip.holu.model.News;
import org.hibernate.Query;
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
}

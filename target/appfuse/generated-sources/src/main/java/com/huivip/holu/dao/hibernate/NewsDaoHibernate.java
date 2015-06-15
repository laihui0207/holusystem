package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.News;
import com.huivip.holu.dao.NewsDao;
import com.huivip.holu.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("newsDao")
public class NewsDaoHibernate extends GenericDaoHibernate<News, Long> implements NewsDao {

    public NewsDaoHibernate() {
        super(News.class);
    }
}

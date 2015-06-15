package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.NewsType;
import com.huivip.holu.dao.NewsTypeDao;
import com.huivip.holu.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("newsTypeDao")
public class NewsTypeDaoHibernate extends GenericDaoHibernate<NewsType, Long> implements NewsTypeDao {

    public NewsTypeDaoHibernate() {
        super(NewsType.class);
    }
}

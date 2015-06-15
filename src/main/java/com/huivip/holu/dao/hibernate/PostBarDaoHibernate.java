package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.PostBar;
import com.huivip.holu.dao.PostBarDao;
import com.huivip.holu.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("postBarDao")
public class PostBarDaoHibernate extends GenericDaoHibernate<PostBar, Long> implements PostBarDao {

    public PostBarDaoHibernate() {
        super(PostBar.class);
    }
}

package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.PostBarDao;
import com.huivip.holu.model.PostBar;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("postBarDao")
public class PostBarDaoHibernate extends GenericDaoHibernate<PostBar, Long> implements PostBarDao {

    public PostBarDaoHibernate() {
        super(PostBar.class);
    }

    @Override
    public List<PostBar> getPostBarListBySubject(String subjectId) {
        String queryString="From PostBar where postSubject.id="+subjectId;
        Query query=getSession().createQuery(queryString);
        return query.list();
    }
}

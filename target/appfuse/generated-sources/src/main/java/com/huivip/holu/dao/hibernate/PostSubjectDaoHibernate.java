package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.PostSubject;
import com.huivip.holu.dao.PostSubjectDao;
import com.huivip.holu.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("postSubjectDao")
public class PostSubjectDaoHibernate extends GenericDaoHibernate<PostSubject, Long> implements PostSubjectDao {

    public PostSubjectDaoHibernate() {
        super(PostSubject.class);
    }
}

package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.Post;
import com.huivip.holu.dao.PostDao;
import com.huivip.holu.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("postDao")
public class PostDaoHibernate extends GenericDaoHibernate<Post, Long> implements PostDao {

    public PostDaoHibernate() {
        super(Post.class);
    }
}

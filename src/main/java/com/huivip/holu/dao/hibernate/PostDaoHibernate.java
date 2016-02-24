package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.PostDao;
import com.huivip.holu.model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("postDao")
public class PostDaoHibernate extends GenericDaoHibernate<Post, Long> implements PostDao {

    public PostDaoHibernate() {
        super(Post.class);
    }

    @Override
    public List<Post> getPostsOfUser(String userID) {
        String sql="";
        return null;
    }
}

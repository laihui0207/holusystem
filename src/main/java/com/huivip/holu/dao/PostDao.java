package com.huivip.holu.dao;

import com.huivip.holu.model.Post;

import java.util.List;

/**
 * An interface that provides a data management interface to the Post table.
 */
public interface PostDao extends GenericDao<Post, Long> {
    List<Post> getPostsOfUser(String userID);

}
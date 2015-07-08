package com.huivip.holu.dao;

import com.huivip.holu.model.PostBar;

import java.util.List;

/**
 * An interface that provides a data management interface to the PostBar table.
 */
public interface PostBarDao extends GenericDao<PostBar, Long> {

    List<PostBar> getPostBarListBySubject(String subjectId);
    public List<PostBar> postBarByUser(String userId);

}
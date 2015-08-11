package com.huivip.holu.dao;

import com.huivip.holu.model.PostBar;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;

import java.util.List;

/**
 * An interface that provides a data management interface to the PostBar table.
 */
public interface PostBarDao extends GenericDao<PostBar, Long> {

    List<PostBar> getPostBarListBySubject(String subjectId,ExtendedPaginatedList list);
    public List<PostBar> postBarByUser(String userId);

}
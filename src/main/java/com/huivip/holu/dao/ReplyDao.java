package com.huivip.holu.dao;

import com.huivip.holu.model.Reply;

import java.util.List;

/**
 * An interface that provides a data management interface to the Reply table.
 */
public interface ReplyDao extends GenericDao<Reply, Long> {

    List<Reply> getReplyByPostbar(String postBarid);
    void deleteByPostBar(String postBarId);

}
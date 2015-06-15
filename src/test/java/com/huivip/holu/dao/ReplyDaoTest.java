package com.huivip.holu.dao;

import com.huivip.holu.dao.BaseDaoTestCase;
import com.huivip.holu.model.Reply;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReplyDaoTest extends BaseDaoTestCase {
    @Autowired
    private ReplyDao replyDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveReply() {
        Reply reply = new Reply();

        // enter all required fields

        log.debug("adding reply...");
        reply = replyDao.save(reply);

        reply = replyDao.get(reply.getId());

        assertNotNull(reply.getId());

        log.debug("removing reply...");

        replyDao.remove(reply.getId());

        // should throw DataAccessException 
        replyDao.get(reply.getId());
    }
}
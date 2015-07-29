package com.huivip.holu.dao;

import com.huivip.holu.dao.BaseDaoTestCase;
import com.huivip.holu.model.PostBar;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostBarDaoTest extends BaseDaoTestCase {
    @Autowired
    private PostBarDao postBarDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemovePostBar() {
        PostBar postBar = new PostBar();

        // enter all required fields
        postBar.setIfAccessAllReply(Boolean.FALSE);

        log.debug("adding postBar...");
        postBar = postBarDao.save(postBar);

        postBar = postBarDao.get(postBar.getId());

        assertNotNull(postBar.getId());

        log.debug("removing postBar...");

        postBarDao.remove(postBar.getId());

        // should throw DataAccessException 
        postBarDao.get(postBar.getId());
    }
}
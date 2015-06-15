package com.huivip.holu.dao;

import com.huivip.holu.dao.BaseDaoTestCase;
import com.huivip.holu.model.PostSubject;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostSubjectDaoTest extends BaseDaoTestCase {
    @Autowired
    private PostSubjectDao postSubjectDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemovePostSubject() {
        PostSubject postSubject = new PostSubject();

        // enter all required fields

        log.debug("adding postSubject...");
        postSubject = postSubjectDao.save(postSubject);

        postSubject = postSubjectDao.get(postSubject.getId());

        assertNotNull(postSubject.getId());

        log.debug("removing postSubject...");

        postSubjectDao.remove(postSubject.getId());

        // should throw DataAccessException 
        postSubjectDao.get(postSubject.getId());
    }
}
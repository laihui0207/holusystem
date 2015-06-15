package com.huivip.holu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.huivip.holu.dao.PostSubjectDao;
import com.huivip.holu.model.PostSubject;
import com.huivip.holu.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class PostSubjectManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private PostSubjectManagerImpl manager;

    @Mock
    private PostSubjectDao dao;

    @Test
    public void testGetPostSubject() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final PostSubject postSubject = new PostSubject();
        given(dao.get(id)).willReturn(postSubject);

        //when
        PostSubject result = manager.get(id);

        //then
        assertSame(postSubject, result);
    }

    @Test
    public void testGetPostSubjects() {
        log.debug("testing getAll...");
        //given
        final List<PostSubject> postSubjects = new ArrayList<>();
        given(dao.getAll()).willReturn(postSubjects);

        //when
        List result = manager.getAll();

        //then
        assertSame(postSubjects, result);
    }

    @Test
    public void testSavePostSubject() {
        log.debug("testing save...");

        //given
        final PostSubject postSubject = new PostSubject();
        // enter all required fields

        given(dao.save(postSubject)).willReturn(postSubject);

        //when
        manager.save(postSubject);

        //then
        verify(dao).save(postSubject);
    }

    @Test
    public void testRemovePostSubject() {
        log.debug("testing remove...");

        //given
        final Long id = -11L;
        willDoNothing().given(dao).remove(id);

        //when
        manager.remove(id);

        //then
        verify(dao).remove(id);
    }
}

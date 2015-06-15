package com.huivip.holu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.huivip.holu.dao.PostDao;
import com.huivip.holu.model.Post;
import com.huivip.holu.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class PostManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private PostManagerImpl manager;

    @Mock
    private PostDao dao;

    @Test
    public void testGetPost() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final Post post = new Post();
        given(dao.get(id)).willReturn(post);

        //when
        Post result = manager.get(id);

        //then
        assertSame(post, result);
    }

    @Test
    public void testGetPosts() {
        log.debug("testing getAll...");
        //given
        final List<Post> posts = new ArrayList<>();
        given(dao.getAll()).willReturn(posts);

        //when
        List result = manager.getAll();

        //then
        assertSame(posts, result);
    }

    @Test
    public void testSavePost() {
        log.debug("testing save...");

        //given
        final Post post = new Post();
        // enter all required fields

        given(dao.save(post)).willReturn(post);

        //when
        manager.save(post);

        //then
        verify(dao).save(post);
    }

    @Test
    public void testRemovePost() {
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

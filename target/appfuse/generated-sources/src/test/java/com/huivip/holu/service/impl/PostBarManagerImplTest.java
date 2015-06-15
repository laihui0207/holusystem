package com.huivip.holu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.huivip.holu.dao.PostBarDao;
import com.huivip.holu.model.PostBar;
import com.huivip.holu.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class PostBarManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private PostBarManagerImpl manager;

    @Mock
    private PostBarDao dao;

    @Test
    public void testGetPostBar() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final PostBar postBar = new PostBar();
        given(dao.get(id)).willReturn(postBar);

        //when
        PostBar result = manager.get(id);

        //then
        assertSame(postBar, result);
    }

    @Test
    public void testGetPostBars() {
        log.debug("testing getAll...");
        //given
        final List<PostBar> postBars = new ArrayList<>();
        given(dao.getAll()).willReturn(postBars);

        //when
        List result = manager.getAll();

        //then
        assertSame(postBars, result);
    }

    @Test
    public void testSavePostBar() {
        log.debug("testing save...");

        //given
        final PostBar postBar = new PostBar();
        // enter all required fields
        postBar.setIfAccessAllReply(Boolean.FALSE);

        given(dao.save(postBar)).willReturn(postBar);

        //when
        manager.save(postBar);

        //then
        verify(dao).save(postBar);
    }

    @Test
    public void testRemovePostBar() {
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

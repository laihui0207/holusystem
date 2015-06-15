package com.huivip.holu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.huivip.holu.dao.NewsDao;
import com.huivip.holu.model.News;
import com.huivip.holu.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class NewsManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private NewsManagerImpl manager;

    @Mock
    private NewsDao dao;

    @Test
    public void testGetNews() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final News news = new News();
        given(dao.get(id)).willReturn(news);

        //when
        News result = manager.get(id);

        //then
        assertSame(news, result);
    }

    @Test
    public void testGetNewss() {
        log.debug("testing getAll...");
        //given
        final List<News> newss = new ArrayList<>();
        given(dao.getAll()).willReturn(newss);

        //when
        List result = manager.getAll();

        //then
        assertSame(newss, result);
    }

    @Test
    public void testSaveNews() {
        log.debug("testing save...");

        //given
        final News news = new News();
        // enter all required fields

        given(dao.save(news)).willReturn(news);

        //when
        manager.save(news);

        //then
        verify(dao).save(news);
    }

    @Test
    public void testRemoveNews() {
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

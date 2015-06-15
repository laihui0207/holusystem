package com.huivip.holu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.huivip.holu.dao.NewsTypeDao;
import com.huivip.holu.model.NewsType;
import com.huivip.holu.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class NewsTypeManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private NewsTypeManagerImpl manager;

    @Mock
    private NewsTypeDao dao;

    @Test
    public void testGetNewsType() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final NewsType newsType = new NewsType();
        given(dao.get(id)).willReturn(newsType);

        //when
        NewsType result = manager.get(id);

        //then
        assertSame(newsType, result);
    }

    @Test
    public void testGetNewsTypes() {
        log.debug("testing getAll...");
        //given
        final List<NewsType> newsTypes = new ArrayList<>();
        given(dao.getAll()).willReturn(newsTypes);

        //when
        List result = manager.getAll();

        //then
        assertSame(newsTypes, result);
    }

    @Test
    public void testSaveNewsType() {
        log.debug("testing save...");

        //given
        final NewsType newsType = new NewsType();
        // enter all required fields

        given(dao.save(newsType)).willReturn(newsType);

        //when
        manager.save(newsType);

        //then
        verify(dao).save(newsType);
    }

    @Test
    public void testRemoveNewsType() {
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

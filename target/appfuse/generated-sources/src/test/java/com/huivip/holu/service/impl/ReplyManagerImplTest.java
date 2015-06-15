package com.huivip.holu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.huivip.holu.dao.ReplyDao;
import com.huivip.holu.model.Reply;
import com.huivip.holu.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class ReplyManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private ReplyManagerImpl manager;

    @Mock
    private ReplyDao dao;

    @Test
    public void testGetReply() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final Reply reply = new Reply();
        given(dao.get(id)).willReturn(reply);

        //when
        Reply result = manager.get(id);

        //then
        assertSame(reply, result);
    }

    @Test
    public void testGetReplies() {
        log.debug("testing getAll...");
        //given
        final List<Reply> replies = new ArrayList<>();
        given(dao.getAll()).willReturn(replies);

        //when
        List result = manager.getAll();

        //then
        assertSame(replies, result);
    }

    @Test
    public void testSaveReply() {
        log.debug("testing save...");

        //given
        final Reply reply = new Reply();
        // enter all required fields

        given(dao.save(reply)).willReturn(reply);

        //when
        manager.save(reply);

        //then
        verify(dao).save(reply);
    }

    @Test
    public void testRemoveReply() {
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

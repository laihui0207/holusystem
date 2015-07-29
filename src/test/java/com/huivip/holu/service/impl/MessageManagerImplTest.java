package com.huivip.holu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.huivip.holu.dao.MessageDao;
import com.huivip.holu.model.Message;
import com.huivip.holu.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class MessageManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private MessageManagerImpl manager;

    @Mock
    private MessageDao dao;

    @Test
    public void testGetMessage() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final Message message = new Message();
        given(dao.get(id)).willReturn(message);

        //when
        Message result = manager.get(id);

        //then
        assertSame(message, result);
    }

    @Test
    public void testGetMessages() {
        log.debug("testing getAll...");
        //given
        final List<Message> messages = new ArrayList<>();
        given(dao.getAll()).willReturn(messages);

        //when
        List result = manager.getAll();

        //then
        assertSame(messages, result);
    }

    @Test
    public void testSaveMessage() {
        log.debug("testing save...");

        //given
        final Message message = new Message();
        // enter all required fields
        message.setStatus(248277528);

        given(dao.save(message)).willReturn(message);

        //when
        manager.save(message);

        //then
        verify(dao).save(message);
    }

    @Test
    public void testRemoveMessage() {
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

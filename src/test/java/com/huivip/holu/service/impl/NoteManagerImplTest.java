package com.huivip.holu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.huivip.holu.dao.NoteDao;
import com.huivip.holu.model.Note;
import com.huivip.holu.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class NoteManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private NoteManagerImpl manager;

    @Mock
    private NoteDao dao;

    @Test
    public void testGetNote() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final Note note = new Note();
        given(dao.get(id)).willReturn(note);

        //when
        Note result = manager.get(id);

        //then
        assertSame(note, result);
    }

    @Test
    public void testGetNotes() {
        log.debug("testing getAll...");
        //given
        final List<Note> notes = new ArrayList<>();
        given(dao.getAll()).willReturn(notes);

        //when
        List result = manager.getAll();

        //then
        assertSame(notes, result);
    }

    @Test
    public void testSaveNote() {
        log.debug("testing save...");

        //given
        final Note note = new Note();
        // enter all required fields

        given(dao.save(note)).willReturn(note);

        //when
        manager.save(note);

        //then
        verify(dao).save(note);
    }

    @Test
    public void testRemoveNote() {
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

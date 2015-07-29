package com.huivip.holu.dao;

import com.huivip.holu.dao.BaseDaoTestCase;
import com.huivip.holu.model.Note;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NoteDaoTest extends BaseDaoTestCase {
    @Autowired
    private NoteDao noteDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveNote() {
        Note note = new Note();

        // enter all required fields

        log.debug("adding note...");
        note = noteDao.save(note);

        note = noteDao.get(note.getId());

        assertNotNull(note.getId());

        log.debug("removing note...");

        noteDao.remove(note.getId());

        // should throw DataAccessException 
        noteDao.get(note.getId());
    }
}
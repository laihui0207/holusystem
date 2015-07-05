package com.huivip.holu.service.impl;

import com.huivip.holu.dao.NoteDao;
import com.huivip.holu.dao.UserDao;
import com.huivip.holu.model.Note;
import com.huivip.holu.service.NoteManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import javax.jws.WebService;

@Service("noteManager")
@WebService(serviceName = "NoteService", endpointInterface = "com.huivip.holu.service.NoteManager")
public class NoteManagerImpl extends GenericManagerImpl<Note, Long> implements NoteManager {
    NoteDao noteDao;
    @Autowired
    UserDao userDao;

    @Autowired
    public NoteManagerImpl(NoteDao noteDao) {
        super(noteDao);
        this.noteDao = noteDao;
    }

    @Override
    public List<Note> noteList() {
        return noteDao.getAll();
    }

    @Override
    public List<Note> myNotes(String userId) {

        return noteDao.myNotes(userId);
    }

    @Override
    public Note viewNote(String id) {
        return noteDao.get(Long.parseLong(id));
    }

    @Override
    public String deleteNote(String noteId) {
        noteDao.remove(Long.parseLong(noteId));
        return "OK";
    }

    @Override
    public String saveNote(Note note) {
        noteDao.save(note);
        return "OK";
    }
}
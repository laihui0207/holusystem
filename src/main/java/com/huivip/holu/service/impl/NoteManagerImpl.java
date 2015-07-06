package com.huivip.holu.service.impl;

import com.huivip.holu.dao.NoteDao;
import com.huivip.holu.dao.UserDao;
import com.huivip.holu.model.Note;
import com.huivip.holu.model.User;
import com.huivip.holu.service.NoteManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
    public void deleteNote(String noteId) {
        noteDao.remove(Long.parseLong(noteId));
    }

    @Override
    public Note saveNote(String title, String content, String userId, String noteId) {
        User user=userDao.get(Long.parseLong(userId));
        Note note=new Note();
        if(noteId!=null && noteId.length()>0 && !noteId.equalsIgnoreCase("undefined")){
            note=noteDao.get(Long.parseLong(noteId));
        }
        note.setTitle(title);
        note.setContent(content);
        note.setCreater(user);
        note.setUpdater(user);
        note.setUpdateTime(new Timestamp(new Date().getTime()));
        noteDao.save(note);
        return note;
    }
}
package com.huivip.holu.service.impl;

import com.huivip.holu.dao.NoteDao;
import com.huivip.holu.model.Note;
import com.huivip.holu.service.NoteManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("noteManager")
@WebService(serviceName = "NoteService", endpointInterface = "com.huivip.holu.service.NoteManager")
public class NoteManagerImpl extends GenericManagerImpl<Note, Long> implements NoteManager {
    NoteDao noteDao;

    @Autowired
    public NoteManagerImpl(NoteDao noteDao) {
        super(noteDao);
        this.noteDao = noteDao;
    }
}
package com.huivip.holu.service;

import com.huivip.holu.service.GenericManager;
import com.huivip.holu.model.Note;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface NoteManager extends GenericManager<Note, Long> {
    
}
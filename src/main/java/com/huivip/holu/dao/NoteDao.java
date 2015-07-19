package com.huivip.holu.dao;

import com.huivip.holu.model.Note;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;

import java.util.List;

/**
 * An interface that provides a data management interface to the Note table.
 */
public interface NoteDao extends GenericDao<Note, Long> {

    List<Note> myNotes(String userid,ExtendedPaginatedList list);
}
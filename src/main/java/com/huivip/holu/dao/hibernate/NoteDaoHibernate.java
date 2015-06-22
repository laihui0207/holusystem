package com.huivip.holu.dao.hibernate;

import com.huivip.holu.model.Note;
import com.huivip.holu.dao.NoteDao;
import com.huivip.holu.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("noteDao")
public class NoteDaoHibernate extends GenericDaoHibernate<Note, Long> implements NoteDao {

    public NoteDaoHibernate() {
        super(Note.class);
    }
}

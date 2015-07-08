package com.huivip.holu.dao.hibernate;

import com.huivip.holu.dao.NoteDao;
import com.huivip.holu.model.Note;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("noteDao")
public class NoteDaoHibernate extends GenericDaoHibernate<Note, Long> implements NoteDao {

    public NoteDaoHibernate() {
        super(Note.class);
    }

    @Override
    public List<Note> myNotes(String userid) {
        String queryString="From Note where receiver.id="+userid;
        Query query=getSession().createQuery(queryString);
        return query.list();
    }
}

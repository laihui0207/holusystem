package com.huivip.holu.service.impl;

import com.huivip.holu.dao.NoteDao;
import com.huivip.holu.dao.UserDao;
import com.huivip.holu.dao.UserGroupDao;
import com.huivip.holu.model.Note;
import com.huivip.holu.model.User;
import com.huivip.holu.model.UserGroup;
import com.huivip.holu.service.NoteManager;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import javax.ws.rs.FormParam;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("noteManager")
@WebService(serviceName = "NoteService", endpointInterface = "com.huivip.holu.service.NoteManager")
public class NoteManagerImpl extends GenericManagerImpl<Note, Long> implements NoteManager {
    NoteDao noteDao;
    @Autowired
    UserDao userDao;
    @Autowired
    UserGroupDao userGroupDao;

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
    public List<Note> myNotes(String userId,ExtendedPaginatedList list) {

        return noteDao.myNotes(userId,list);
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

    @Override
    public Note sendNote( String noteId,String users,String groups,String userId) {
        User user=userDao.get(Long.parseLong(userId));
        Note note=noteDao.get(Long.parseLong(noteId));
        List<User> receiverList=new ArrayList<>();
        if(null!=users && users.length()>0 && !users.equalsIgnoreCase("undefined")){
            if(note.getSendToUserList()!=null){
                note.getSendToUserList().clear();
            }
            String[] usersArray=users.split(";");
            for(String id:usersArray){
              User user1=userDao.get(Long.parseLong(id));
                if(!receiverList.contains(user1)){
                    receiverList.add(user1);
                }
                note.getSendToUserList().add(user1);
            }
        }
        if(null!=groups && groups.length()>0 && !groups.equalsIgnoreCase("undefined")){
            if(note.getSendToUserGroupList()!=null){
                note.getSendToUserGroupList().clear();
            }
            String[] groupArray=groups.split(";");
            for(String id:groupArray){
                UserGroup ug=userGroupDao.get(Long.parseLong(id));
                note.getSendToUserGroupList().add(ug);
                for(User u:ug.getMembers()){
                    if(!receiverList.contains(u)){
                        receiverList.add(u);
                    }
                }
            }
        }
        noteDao.save(note);
        Note copyNote=new Note();
        copyNote.setTitle(note.getTitle());
        copyNote.setContent(note.getContent());
        copyNote.setCreater(note.getCreater());
        copyNote.setCreateTime(note.getCreateTime());
        copyNote.setFromUser(user);

        for(User u:receiverList){
            copyNote.setId(null);
            copyNote.setReceiver(u);
            noteDao.save(copyNote);
        }

        return note;
    }
}
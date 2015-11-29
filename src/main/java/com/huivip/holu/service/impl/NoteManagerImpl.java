package com.huivip.holu.service.impl;

import com.huivip.holu.dao.NoteDao;
import com.huivip.holu.dao.UserDao;
import com.huivip.holu.model.CustomGroup;
import com.huivip.holu.model.Department;
import com.huivip.holu.model.Note;
import com.huivip.holu.model.User;
import com.huivip.holu.service.CustomGroupManager;
import com.huivip.holu.service.DepartmentManager;
import com.huivip.holu.service.NoteManager;
import com.huivip.holu.util.cache.Cache2kProvider;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import com.huivip.holu.webapp.helper.PaginatedListImpl;
import org.cache2k.Cache;
import org.cache2k.CacheBuilder;
import org.displaytag.properties.SortOrderEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
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
    CustomGroupManager customGroupManager;
    @Autowired
    DepartmentManager departmentManager;

    Cache<String,Note> cache=null;

    @Autowired
    public NoteManagerImpl(NoteDao noteDao) {
        super(noteDao);
        this.noteDao = noteDao;
        cache=Cache2kProvider.getinstance().setCache(Note.class, CacheBuilder.newCache(String.class,Note.class).build());
    }

    @Override
    public List<Note> noteList(String userId,String page,String pageSize) {
        ExtendedPaginatedList list=new PaginatedListImpl();
        list.setIndex(Integer.parseInt(page));
        list.setPageSize(Integer.parseInt(pageSize));
        list.setSortCriterion("createTime");
        list.setSortDirection(SortOrderEnum.DESCENDING);
        String cacheKey=Note.LIST_CACHE+userId+"_"+page+"_"+pageSize;
        maintainCacheKey(Note.LIST_NEWS_CACHE_KEY,cacheKey);
        ExtendedPaginatedList listFromCache=listCache.peek(cacheKey);
        if(listFromCache==null){
            myNotes(userId,list);
            listCache.put(cacheKey,list);
        }
        else {
            list=listFromCache;
        }
        return list.getList();
    }

    @Override
    public List<Note> myNotes(String userId,ExtendedPaginatedList list) {

        return noteDao.myNotes(userId,list);
    }

    @Override
    public Note viewNote(String id) {
        Note note=cache.peek(id);
        if(note==null){
            note=noteDao.get(Long.parseLong(id));
            cache.put(id,note);
        }
        return note;
    }

    @Override
    public void deleteNote(String noteId) {
        noteDao.remove(Long.parseLong(noteId));
        cache.remove(noteId);
        removeCacheByKeyPrefix(Note.LIST_NEWS_CACHE_KEY,Note.LIST_CACHE);
    }

    @Override
    public Note saveNote(String title, String content, String userId, String noteId) {
        User user=userDao.get(Long.parseLong(userId));
        Note note=new Note();
        if(noteId!=null && noteId.length()>0 && !noteId.equalsIgnoreCase("undefined")){
            note=cache.peek(noteId);
            if(note==null){
                note=noteDao.get(Long.parseLong(noteId));
            }
        }
        note.setTitle(title);
        note.setContent(content);
        note.setCreater(user);
        note.setUpdater(user);
        note.setReceiver(user);
        note.setUpdateTime(new Timestamp(new Date().getTime()));
        Note note1=noteDao.save(note);
        cache.put(note1.getId().toString(),note1);
        removeCacheByKeyPrefix(Note.LIST_NEWS_CACHE_KEY,Note.LIST_CACHE);
        return note;
    }

    @Override
    public Note sendNote( String noteId,String users,String groups,String departments,String userId) {
        User user=userDao.get(Long.parseLong(userId));
        Note note=cache.peek(noteId);
        if(note==null){
            note=noteDao.get(Long.parseLong(noteId));
        }
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
                CustomGroup ug=customGroupManager.get(Long.parseLong(id));
                note.getSendToUserGroupList().add(ug);
                for(User u:ug.getMembers()){
                    if(!receiverList.contains(u)){
                        receiverList.add(u);
                    }
                }
            }
        }
        if(null!=departments && departments.length()>0 && !departments.equalsIgnoreCase("undefined")){
            String[] departmentArray=departments.split(";");
            for(String id: departmentArray){
                Department department=departmentManager.getDepartmentByDepartmentID(id);
                for(User u:department.getUsers()){
                    if(!receiverList.contains(u)){
                        receiverList.add(u);
                    }
                }
            }
        }
        Note savedNote=noteDao.save(note);
        cache.put(savedNote.getId().toString(),savedNote);
        Note copyNote=new Note();
        copyNote.setTitle(note.getTitle());
        copyNote.setContent(note.getContent());
        copyNote.setCreater(note.getCreater());
        copyNote.setCreateTime(note.getCreateTime());
        copyNote.setFromUser(user);

        for(User u:receiverList){
            copyNote.setId(null);
            copyNote.setReceiver(u);
            Note newNote=noteDao.save(copyNote);
            cache.put(newNote.getId().toString(),newNote);
        }
        removeCacheByKeyPrefix(Note.LIST_NEWS_CACHE_KEY,Note.LIST_CACHE);
        return note;
    }
}
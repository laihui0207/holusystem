package com.huivip.holu.service.impl;

import com.huivip.holu.dao.*;
import com.huivip.holu.model.*;
import com.huivip.holu.service.PostBarManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("postBarManager")
@WebService(serviceName = "PostBarService", endpointInterface = "com.huivip.holu.service.PostBarManager")
public class PostBarManagerImpl extends GenericManagerImpl<PostBar, Long> implements PostBarManager {
    PostBarDao postBarDao;
    @Autowired
    UserDao userDao;
    @Autowired
    PostSubjectDao postSubjectDao;
    @Autowired
    CustomGroupDao customGroupDao;

    @Autowired
    public PostBarManagerImpl(PostBarDao postBarDao) {
        super(postBarDao);
        this.postBarDao = postBarDao;
    }

    @Override
    public List<PostBar> postBarBySubject(String subjectId) {
        return postBarDao.getPostBarListBySubject(subjectId);
    }

    @Override
    public List<PostBar> postBarByUser(String userId) {
        return postBarDao.postBarByUser(userId);
    }

    @Override
    public PostBar getPostBar(String postBarId) {
        return postBarDao.get(Long.parseLong(postBarId));
    }

    @Override
    public List<SelectLabelValue> getViewUsersLabelValue(String postBarId) {
        PostBar postBar=null;
        if(null!=postBarId && postBarId.length()>0 && !postBarId.equalsIgnoreCase("undefined")){
            postBar=postBarDao.get(Long.parseLong(postBarId));
        }
        List<User> list=userDao.getAllDistinct();
        List<SelectLabelValue> result=new ArrayList<>();
        for(User user:list){
            SelectLabelValue slv=new SelectLabelValue();
            slv.setId(user.getId().toString());
            slv.setText(user.getFullName());
            if(null!=postBar && postBar.getViewUsers().contains(user)){
                slv.setChecked(true);
            }
            else {
                slv.setChecked(false);
            }
            slv.setIcon(null);
            result.add(slv);
        }
        return result;
    }

    @Override
    public List<SelectLabelValue> getReplyUsersLabelValue(String postBarId) {
        PostBar postBar=null;
        if(null!=postBarId && postBarId.length()>0 && !postBarId.equalsIgnoreCase("undefined")){
            postBar=postBarDao.get(Long.parseLong(postBarId));
        }
        List<User> list=userDao.getAllDistinct();
        List<SelectLabelValue> result=new ArrayList<>();
        for(User user:list){
            SelectLabelValue slv=new SelectLabelValue();
            slv.setId(user.getId().toString());
            slv.setText(user.getFullName());
            if(null!=postBar && postBar.getReplyUsers().contains(user)){
                slv.setChecked(true);
            }
            else {
                slv.setChecked(false);
            }
            slv.setIcon(null);
            result.add(slv);
        }
        return result;
    }

    @Override
    public List<SelectLabelValue> getViewGroupsLabelValue(String postBarId) {
        PostBar postBar=null;
        if(null!=postBarId && postBarId.length()>0 && !postBarId.equalsIgnoreCase("undefined")){
            postBar=postBarDao.get(Long.parseLong(postBarId));
        }
        List<CustomGroup> list=customGroupDao.getAll();
        List<SelectLabelValue> result=new ArrayList<>();
        for(CustomGroup ug:list){
            SelectLabelValue slv=new SelectLabelValue();
            slv.setIcon(null);
            slv.setId(ug.getId().toString());
            slv.setText(ug.getName());
            if(null!=postBar && postBar.getViewGroups().contains(ug)){
                slv.setChecked(true);
            }
            else {
                slv.setChecked(false);
            }
            result.add(slv);
        }
        return result;
    }

    @Override
    public List<SelectLabelValue> getReplyGroupsLabelValue(String postBarId) {
        PostBar postBar=null;
        if(null!=postBarId && postBarId.length()>0 && !postBarId.equalsIgnoreCase("undefined")){
            postBar=postBarDao.get(Long.parseLong(postBarId));
        }
        List<CustomGroup> list=customGroupDao.getAll();
        List<SelectLabelValue> result=new ArrayList<>();
        for(CustomGroup ug:list){
            SelectLabelValue slv=new SelectLabelValue();
            slv.setIcon(null);
            slv.setId(ug.getId().toString());
            slv.setText(ug.getName());
            if(null!=postBar && postBar.getReplyGroups().contains(ug)){
                slv.setChecked(true);
            }
            else {
                slv.setChecked(false);
            }
            result.add(slv);
        }
        return result;
    }

    @Override
    public void deletePostBar(String id) {
        postBarDao.remove(Long.parseLong(id));
    }

    @Override
    public PostBar savePostBar(String title,String content, String subjectId, String userId, String postBarId,
        String viewUsers,String viewGroups,String replyUsers,String replyGroups) {
        User user=userDao.get(Long.parseLong(userId));
        PostBar postBar=new PostBar();
        if(null!=postBarId && postBarId.length()>0 && !postBarId.equalsIgnoreCase("undefined")){
            postBar=postBarDao.get(Long.parseLong(postBarId));
        }
        postBar.setContent(content);
        postBar.setCreater(user);
        postBar.setUpdateTime(new Timestamp(new Date().getTime()));
        postBar.setPostSubject(postSubjectDao.get(Long.parseLong(subjectId)));
        postBar.setTitle(title);

        if(null!=viewUsers && viewUsers.length()>0 && !viewUsers.equalsIgnoreCase("undefined")){
            postBar.setIfAccessAllView(false);
            if(postBar.getViewUsers()!=null){
                postBar.getViewUsers().clear();
            }
            String[] viewUserArray=viewUsers.split(";");
            for(String id:viewUserArray){
                postBar.getViewUsers().add(userDao.get(Long.parseLong(id)));
            }
        }
        if(null!=viewGroups && viewGroups.length()>0 && !viewGroups.equalsIgnoreCase("undefined")){
            postBar.setIfAccessAllView(false);
            if(postBar.getViewGroups()!=null){
                postBar.getViewGroups().clear();
            }
            String[] viewGroupArray=viewGroups.split(";");
            for(String id:viewGroupArray){
                postBar.getViewGroups().add(customGroupDao.get(Long.parseLong(id)));
            }
        }
        if(null!=replyUsers && replyUsers.length()>0 && !replyUsers.equalsIgnoreCase("undefined")){
            postBar.setIfAccessAllReply(false);
            if(postBar.getReplyUsers()!=null){
                postBar.getReplyUsers().clear();
            }
            String[] replyUsersArray=replyUsers.split(";");
            for(String id:replyUsersArray){
                postBar.getReplyUsers().add(userDao.get(Long.parseLong(id)));
            }
        }
        if(null!=replyGroups && replyGroups.length()>0 && !replyGroups.equalsIgnoreCase("undefined")){
            postBar.setIfAccessAllReply(false);
            if(postBar.getReplyGroups()!=null){
                postBar.getReplyGroups().clear();
            }
            String[] replyGroupsArray=replyGroups.split(";");
            for(String id:replyGroupsArray){
                postBar.getReplyGroups().add(customGroupDao.get(Long.parseLong(id)));
            }
        }
        postBarDao.save(postBar);
        return postBar;
    }
}
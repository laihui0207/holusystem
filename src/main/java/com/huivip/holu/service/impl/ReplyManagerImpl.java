package com.huivip.holu.service.impl;

import com.huivip.holu.dao.PostBarDao;
import com.huivip.holu.dao.ReplyDao;
import com.huivip.holu.dao.UserDao;
import com.huivip.holu.model.PostBar;
import com.huivip.holu.model.Reply;
import com.huivip.holu.model.User;
import com.huivip.holu.service.ReplyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service("replyManager")
@WebService(serviceName = "ReplyService", endpointInterface = "com.huivip.holu.service.ReplyManager")
public class ReplyManagerImpl extends GenericManagerImpl<Reply, Long> implements ReplyManager {
    ReplyDao replyDao;
    @Autowired
    UserDao userDao;
    @Autowired
    PostBarDao postBarDao;

    @Autowired
    public ReplyManagerImpl(ReplyDao replyDao) {
        super(replyDao);
        this.replyDao = replyDao;
    }

    @Override
    public List<Reply> getReplyByPostbar(String postBarid) {
        return replyDao.getReplyByPostbar(postBarid);
    }

    @Override
    public Reply saveReply(String content, String postBarId, String userId, String replyId) {
        User user=userDao.get(Long.parseLong(userId));
        PostBar postBar=postBarDao.get(Long.parseLong(postBarId));
        Reply reply=new Reply();
        if(null!=replyId && replyId.length()>0 && !replyId.equalsIgnoreCase("undefined")){
            reply=replyDao.get(Long.parseLong(replyId));
        }
        reply.setReplier(user);
        reply.setPostBar(postBar);
        reply.setContent(content);
        reply.setUpdateTime(new Timestamp(new Date().getTime()));
        replyDao.save(reply);
        postBar.setLastReplyTime(new Timestamp(new Date().getTime()));
        postBar.setLastReplyUser(user);
        postBarDao.save(postBar);
        return reply;
    }

    @Override
    public void deleteByPostBar(String postBarId) {
        replyDao.deleteByPostBar(postBarId);
    }
}
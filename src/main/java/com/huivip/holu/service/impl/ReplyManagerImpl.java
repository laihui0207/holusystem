package com.huivip.holu.service.impl;

import com.huivip.holu.dao.ReplyDao;
import com.huivip.holu.model.Reply;
import com.huivip.holu.service.ReplyManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("replyManager")
@WebService(serviceName = "ReplyService", endpointInterface = "com.huivip.holu.service.ReplyManager")
public class ReplyManagerImpl extends GenericManagerImpl<Reply, Long> implements ReplyManager {
    ReplyDao replyDao;

    @Autowired
    public ReplyManagerImpl(ReplyDao replyDao) {
        super(replyDao);
        this.replyDao = replyDao;
    }
}
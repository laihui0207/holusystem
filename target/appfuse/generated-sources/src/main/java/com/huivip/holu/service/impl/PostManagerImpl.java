package com.huivip.holu.service.impl;

import com.huivip.holu.dao.PostDao;
import com.huivip.holu.model.Post;
import com.huivip.holu.service.PostManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("postManager")
@WebService(serviceName = "PostService", endpointInterface = "com.huivip.holu.service.PostManager")
public class PostManagerImpl extends GenericManagerImpl<Post, Long> implements PostManager {
    PostDao postDao;

    @Autowired
    public PostManagerImpl(PostDao postDao) {
        super(postDao);
        this.postDao = postDao;
    }
}
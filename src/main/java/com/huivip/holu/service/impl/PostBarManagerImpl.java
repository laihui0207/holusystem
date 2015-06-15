package com.huivip.holu.service.impl;

import com.huivip.holu.dao.PostBarDao;
import com.huivip.holu.model.PostBar;
import com.huivip.holu.service.PostBarManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("postBarManager")
@WebService(serviceName = "PostBarService", endpointInterface = "com.huivip.holu.service.PostBarManager")
public class PostBarManagerImpl extends GenericManagerImpl<PostBar, Long> implements PostBarManager {
    PostBarDao postBarDao;

    @Autowired
    public PostBarManagerImpl(PostBarDao postBarDao) {
        super(postBarDao);
        this.postBarDao = postBarDao;
    }
}
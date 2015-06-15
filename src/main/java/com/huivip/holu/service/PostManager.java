package com.huivip.holu.service;

import com.huivip.holu.service.GenericManager;
import com.huivip.holu.model.Post;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface PostManager extends GenericManager<Post, Long> {
    
}
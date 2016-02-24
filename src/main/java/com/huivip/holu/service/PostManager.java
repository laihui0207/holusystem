package com.huivip.holu.service;

import com.huivip.holu.model.Post;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface PostManager extends GenericManager<Post, Long> {
    List<Post> getPostsOfUser(String userID);
    
}
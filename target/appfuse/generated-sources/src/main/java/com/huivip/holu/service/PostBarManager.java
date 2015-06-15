package com.huivip.holu.service;

import com.huivip.holu.service.GenericManager;
import com.huivip.holu.model.PostBar;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface PostBarManager extends GenericManager<PostBar, Long> {
    
}
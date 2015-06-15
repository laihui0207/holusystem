package com.huivip.holu.service;

import com.huivip.holu.service.GenericManager;
import com.huivip.holu.model.PostSubject;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface PostSubjectManager extends GenericManager<PostSubject, Long> {
    
}
package com.huivip.holu.service;

import com.huivip.holu.service.GenericManager;
import com.huivip.holu.model.Reply;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface ReplyManager extends GenericManager<Reply, Long> {
    
}
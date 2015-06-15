package com.huivip.holu.service;

import com.huivip.holu.service.GenericManager;
import com.huivip.holu.model.Message;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface MessageManager extends GenericManager<Message, Long> {
    
}
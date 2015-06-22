package com.huivip.holu.service;

import com.huivip.holu.model.Message;
import com.huivip.holu.model.User;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface MessageManager extends GenericManager<Message, Long> {

    List<Message> messageByOwner(User user);
    
}
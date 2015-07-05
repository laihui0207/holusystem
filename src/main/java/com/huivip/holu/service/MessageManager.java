package com.huivip.holu.service;

import com.huivip.holu.model.Message;
import com.huivip.holu.model.User;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@WebService
@Path("/messages")
public interface MessageManager extends GenericManager<Message, Long> {
    List<Message> messageByOwner(User user);

    @Path("/user/{userId}")
    @GET
    List<Message> myMessage(String userId);
}
package com.huivip.holu.service;

import com.huivip.holu.model.Message;
import com.huivip.holu.model.User;

import javax.jws.WebService;
import javax.ws.rs.*;
import java.util.List;

@WebService
@Path("/msgs")
public interface MessageManager extends GenericManager<Message, Long> {
    List<Message> messageByOwner(User user);

    @GET
    @Path("user/{userId}")
    List<Message> myMessage(@PathParam("userId")String userId);

    @GET
    @Path("{id}")
    Message getMessage(@PathParam("id")String id);



    @GET
    @Path("{id}/delete")
    void deleteMessage(@PathParam("id")String messageId);

    @POST
    Message saveMessage(@FormParam("title") String title, @FormParam("content") String content,
                        @FormParam("userId") String userId,@FormParam("messageId") String messageId);

    @POST
    @Path("Send")
    Message sendNote(@FormParam("messageId")String messageId,@FormParam("users")String users,
                  @FormParam("groups")String groups,@FormParam("userId")String userId);
}
package com.huivip.holu.service;

import com.huivip.holu.model.Message;
import com.huivip.holu.model.MessageReceiver;
import com.huivip.holu.model.User;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.apache.cxf.annotations.GZIP;

import javax.jws.WebService;
import javax.ws.rs.*;
import java.util.List;

@WebService
@GZIP
@Path("/msgs")
public interface MessageManager extends GenericManager<Message, Long> {
    List<Message> messageByOwner(User user,ExtendedPaginatedList list);

    @GET
    @Path("user/{userId}")
    List<MessageReceiver> myMessage(@PathParam("userId") String userId,
                                    @DefaultValue("all") @QueryParam("type") String type,
                            @DefaultValue("0") @QueryParam("page") String page,
                            @DefaultValue("10") @QueryParam("pageSize")String pageSize);
    @GET
    @Path("user/{userId}/count")
    int newMessageCount(@PathParam("userId")String userId);

    @GET
    @Path("{id}")
    Message getMessage(@PathParam("id")String id);

    @GET
    @Path("{id}/{userId}/read")
    Message readMessage(@PathParam("id") String id,@PathParam("userId")String userId);


    @GET
    @Path("{id}/delete")
    void deleteMessage(@PathParam("id")String messageId);

    @POST
    Message saveMessage(@FormParam("title") String title, @FormParam("content") String content,
                        @FormParam("userId") String userId,@FormParam("messageId") String messageId);

    @POST
    @Path("Send")
    Message sendMessage(@FormParam("messageId") String messageId, @FormParam("users") String users,
                        @FormParam("groups") String groups,@FormParam("departments") String departments ,
                        @FormParam("userId") String userId);
}
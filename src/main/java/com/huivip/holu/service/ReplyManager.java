package com.huivip.holu.service;

import com.huivip.holu.model.Reply;

import javax.jws.WebService;
import javax.ws.rs.*;
import java.util.List;

@WebService
@Path("/replies")
public interface ReplyManager extends GenericManager<Reply, Long> {
    @GET
    @Path("postbar/{postBarId}")
    List<Reply> getReplyByPostbar(@PathParam("postBarId")String postBarid);
    @POST
    Reply saveReply(@FormParam("content")String content,@FormParam("postBarId")String postBarId,
                    @FormParam("userId") String userId,@FormParam("replyId")String replyId);
}

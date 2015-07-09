package com.huivip.holu.service;

import com.huivip.holu.model.Reply;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@WebService
@Path("/replies")
public interface ReplyManager extends GenericManager<Reply, Long> {
    @GET
    @Path("postbar/{postBarId}")
    List<Reply> getReplyByPostbar(@PathParam("postBarId")String postBarid);
}
package com.huivip.holu.service;

import com.huivip.holu.model.PostBar;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@WebService
@Path("/postbars")
public interface PostBarManager extends GenericManager<PostBar, Long> {

    @GET
    @Path("subject/{id}")
    List<PostBar> postBarBySubject(@PathParam("id")String subjectId);

    @GET
    @Path("user/{userId}")
    List<PostBar> postBarByUser(@PathParam("userId")String userId);

    @GET
    @Path("{id}")
    PostBar getPostBar(@PathParam("id")String postBarId);
}
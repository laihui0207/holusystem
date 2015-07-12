package com.huivip.holu.service;

import com.huivip.holu.model.PostBar;
import com.huivip.holu.model.SelectLabelValue;

import javax.jws.WebService;
import javax.ws.rs.*;
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

    @GET
    @Path("{postBarId}/viewUser/slv")
    List<SelectLabelValue> getViewUsersLabelValue(@PathParam("postBarId")String postBarId);
    @GET
    @Path("{postBarId}/replyUser/slv")
    List<SelectLabelValue> getReplyUsersLabelValue(@PathParam("postBarId")String postBarId);
    @GET
    @Path("{postBarId}/viewGroup/slv")
    List<SelectLabelValue> getViewGroupsLabelValue(@PathParam("postBarId")String postBarId);
    @GET
    @Path("{postBarId}/replyGroup/slv")
    List<SelectLabelValue> getReplyGroupsLabelValue(@PathParam("postBarId")String postBarId);
    @GET
    @Path("{id}/delete")
    void deletePostBar(@PathParam("id")String id);
    @POST
    PostBar savePostBar( @FormParam("title") String title, @FormParam("content") String content,
                        @FormParam("subjectId") String subjectId, @FormParam("userId") String userId,
                         @FormParam("postBarId") String postBarId,@FormParam("viewUsers")String viewUsers,
                         @FormParam("viewGroups")String viewGroups,
                         @FormParam("replyUsers")String replyUsers,@FormParam("replyGroups")String replyGroups);

}
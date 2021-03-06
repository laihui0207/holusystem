package com.huivip.holu.service;

import com.huivip.holu.model.Note;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;

import javax.jws.WebService;
import javax.ws.rs.*;
import java.util.List;

@WebService
@Path("/notes")
public interface NoteManager extends GenericManager<Note, Long> {

    @GET
    @Path("user/{userID}")
    List<Note> noteList(@PathParam("userID")String userId,@DefaultValue("0") @QueryParam("page") String pageIndex,
                        @DefaultValue("25") @QueryParam("pageSize") String pageSize);

    @GET
    @Path("mynote/{userid}")
    List<Note> myNotes(@PathParam("userid")String userId,ExtendedPaginatedList list);

    @Path("{id}")
    @GET
    Note viewNote(@PathParam("id")String id);

    @GET
    @Path("{id}/delete")
    void deleteNote(@PathParam("id") String noteId);

    @POST
    Note saveNote(@FormParam("title") String title, @FormParam("content") String content,
                  @FormParam("userId") String userId,@FormParam("noteId") String noteId);

    @POST
    @Path("Send")
    Note sendNote(@FormParam("noteId")String noteId,@FormParam("users")String users,
                  @FormParam("groups")String groups,@FormParam("departments")String departments,@FormParam("userId")String userId);

}
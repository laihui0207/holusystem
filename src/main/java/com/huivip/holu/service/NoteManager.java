package com.huivip.holu.service;

import com.huivip.holu.model.Note;

import javax.jws.WebService;
import javax.ws.rs.*;
import java.util.List;

@WebService
@Path("/notes")
public interface NoteManager extends GenericManager<Note, Long> {

    @GET
    List<Note> noteList();

    @GET
    @Path("mynote/{userid}")
    List<Note> myNotes(@PathParam("userid")String userId);

    @Path("{id}")
    @GET
    Note viewNote(@PathParam("id")String id);

    @GET
    @Path("{id}/delete")
    String deleteNote(@PathParam("id")String noteId);

    @POST
    Note saveNote(@FormParam("title") String title, @FormParam("content") String content, @FormParam("userId") String userId);

}
package com.huivip.holu.service;

import com.huivip.holu.model.Note;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    String saveNote(Note note);

}
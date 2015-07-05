package com.huivip.holu.service;

import com.huivip.holu.model.PostSubject;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@WebService
@Path("/postSubjects")
public interface PostSubjectManager extends GenericManager<PostSubject, Long> {
   @GET
   List<PostSubject> postSubjects();
}
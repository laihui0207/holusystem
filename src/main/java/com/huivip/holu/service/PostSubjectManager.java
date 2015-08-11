package com.huivip.holu.service;

import com.huivip.holu.model.PostSubject;

import javax.jws.WebService;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@WebService
@Path("/postSubjects")
public interface PostSubjectManager extends GenericManager<PostSubject, Long> {
   @GET
   List<PostSubject> postSubjects(@DefaultValue("0") @QueryParam("page") String pageIndex,
                                  @DefaultValue("10") @QueryParam("pageSize") String pageSize);
}
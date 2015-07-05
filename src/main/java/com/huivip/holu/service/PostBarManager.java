package com.huivip.holu.service;

import com.huivip.holu.model.PostBar;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@WebService
@Path("/postbars")
public interface PostBarManager extends GenericManager<PostBar, Long> {

    @GET
    List<PostBar> postBarBySubject(String subjectId);


}
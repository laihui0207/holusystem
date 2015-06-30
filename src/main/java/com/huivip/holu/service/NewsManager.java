package com.huivip.holu.service;

import com.huivip.holu.model.News;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@WebService
@Path("/news")
public interface NewsManager extends GenericManager<News, Long> {

    @GET
    List<News> getNewss();

    @Path("{id}")
    @GET
    News getNews(@PathParam("id")String id);
}
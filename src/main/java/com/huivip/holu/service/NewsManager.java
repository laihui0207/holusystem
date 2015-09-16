package com.huivip.holu.service;

import com.huivip.holu.model.News;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

@WebService
@Path("/news")
public interface NewsManager extends GenericManager<News, Long> {

    @GET
    List<News> getNewss(@DefaultValue("0") @QueryParam("page") String pageIndex,
                        @DefaultValue("10") @QueryParam("pageSize") String pageSize,
                        @DefaultValue("all") @QueryParam("type") String newsType);

    @GET
    @Path("important")
    List<News> getImportantNewss(@DefaultValue("0") @QueryParam("page") String pageIndex,
                        @DefaultValue("10") @QueryParam("pageSize") String pageSize);
    @Path("{id}")
    @GET
    News getNews(@PathParam("id")String id,@QueryParam("client") String clientType,@Context HttpServletRequest request);

    @Path("newstype/{typeId}")
    @GET
    List<News> getNewsByType(@PathParam("typeId") String typeID);
}
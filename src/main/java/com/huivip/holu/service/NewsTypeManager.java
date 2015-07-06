package com.huivip.holu.service;

import com.huivip.holu.model.NewsType;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@WebService
@Path("/newstypes")
public interface NewsTypeManager extends GenericManager<NewsType, Long> {
    @GET
    List<NewsType> newsTypeList();
}
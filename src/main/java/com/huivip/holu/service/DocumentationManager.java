package com.huivip.holu.service;

import com.huivip.holu.model.Documentation;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;

@WebService
@Path("/Documentations")
public interface DocumentationManager extends GenericManager<Documentation, Long> {
    @GET
    List<Documentation> getDocumentations();

    @GET
    @Path("user/{userId}")
    List<Documentation> myDocumentations(@PathParam("userId")String userId,ExtendedPaginatedList list);

    @GET
    @Path("{id}/download/{userId}")
    Response downloadDocumentation(@PathParam("id") String id,@PathParam("userId")String userId);
    
}
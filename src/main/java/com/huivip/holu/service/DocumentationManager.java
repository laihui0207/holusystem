package com.huivip.holu.service;

import com.huivip.holu.model.Documentation;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@WebService
@Path("/Documentations")
public interface DocumentationManager extends GenericManager<Documentation, Long> {

    @GET
    List<Documentation> getDocumentations();

    @GET
    @Path("user/{userId}")
    List<Documentation> myDocumentations(@PathParam("userId")String userId);

    @GET
    @Path("{id}/download")
    Documentation downloadDocumentation(@PathParam("id") String id);
    
}
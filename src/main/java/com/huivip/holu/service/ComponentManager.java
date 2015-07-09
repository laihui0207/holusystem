package com.huivip.holu.service;

import com.huivip.holu.model.Component;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@WebService
@Path("/components")
public interface ComponentManager extends GenericManager<Component, Long> {
    @GET
    @Path("project/{projectId}")
    List<Component> listComponentByProject(@PathParam("projectId")String projectID);
    
}
package com.huivip.holu.service;

import com.huivip.holu.model.Component;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;

import javax.jws.WebService;
import javax.ws.rs.*;
import java.util.List;

@WebService
@Path("/components")
public interface ComponentManager extends GenericManager<Component, Long> {
    @GET
    @Path("project/{projectId}/u/{userID}")
    List<Component> listComponentByProject(@PathParam("projectId")String projectID,
                                           @PathParam("userID")String userId,
                                           @DefaultValue("0") @QueryParam("page") String page,
                                           @DefaultValue("10") @QueryParam("pageSize") String pageSize);
    List<Component> listComponentByProject(String projectID,String userId,ExtendedPaginatedList list);
    @GET
    @Path("{componentID}/{userID}")
    Component getComponentByComponentID(@PathParam("componentID")String componentID,@PathParam("userID")String userID);
    
}
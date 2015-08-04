package com.huivip.holu.service;

import com.huivip.holu.model.Component;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@WebService
@Path("/components")
public interface ComponentManager extends GenericManager<Component, Long> {
    @GET
    @Path("project/{projectId}/u/{userID}")
    List<Component> listComponentByProject(@PathParam("projectId")String projectID,@PathParam("userID")String userId);
    List<Component> listComponentByProject(String projectID,String userId,ExtendedPaginatedList list);
    @GET
    @Path("{componentID}/{userID}")
    Component getComponentByComponentID(@PathParam("componentID")String componentID,@PathParam("userID")String userID);
    
}
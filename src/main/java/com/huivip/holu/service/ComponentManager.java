package com.huivip.holu.service;

import com.huivip.holu.model.Component;
import com.huivip.holu.model.ComponentStyle;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.apache.cxf.annotations.GZIP;

import javax.jws.WebService;
import javax.ws.rs.*;
import java.util.List;

@WebService
@GZIP
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
    /*@GET
    @Path("{userID}")*/
    List<Component> listComponentByUser(@PathParam("userID") String userID);
    @GET
    @Path("{userID}")
    List<String> listComponentIdsByUser(@PathParam("userID")String userID);
    List<Component> getComponentListOfProject(String userId,String projectID);
    @GET
    @Path("style/{projectID}/{userID}")
    List<ComponentStyle> listAllComponentStyleOfProject(@PathParam("projectID") String projectID,@PathParam("userID") String userID);
}
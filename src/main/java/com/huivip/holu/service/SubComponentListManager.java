package com.huivip.holu.service;

import com.huivip.holu.model.Component;
import com.huivip.holu.model.SubComponentList;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;

import javax.jws.WebService;
import javax.ws.rs.*;
import java.util.List;

@WebService
@Path("/subComponents")
public interface SubComponentListManager extends GenericManager<SubComponentList, Long> {

    List<SubComponentList> getSubComponentListByComponentID(String componentID,String userID,ExtendedPaginatedList list);
    Component getParentComponent(String subComponentId,String userId);
    @GET
    @Path("list/{componentID}/{userID}")
    List<SubComponentList> getSubComponentListByComponentID(@PathParam("componentID")String componentID,
                                                            @PathParam("userID")String userID,
                                                            @DefaultValue("0") @QueryParam("page") String page,
                                                            @DefaultValue("10") @QueryParam("pageSize") String pageSize);
    @GET
    @Path("{componentID}/{userID}")
    SubComponentList getSubComponentBySubComponentID(@PathParam("componentID")String subComponentID,@PathParam("userID")String userID);

    @GET
    @Path("{componentID}/{userID}/parent")
    Component getParentCompoentBySubComponentID(@PathParam("componentID")String subComponentID,@PathParam("userID")String userID);
}
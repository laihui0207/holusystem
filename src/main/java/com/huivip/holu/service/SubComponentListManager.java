package com.huivip.holu.service;

import com.huivip.holu.model.SubComponentList;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@WebService
@Path("/subComponents")
public interface SubComponentListManager extends GenericManager<SubComponentList, Long> {

    List<SubComponentList> getSubComponentListByComponentID(String componentID,String userID,ExtendedPaginatedList list);
    @GET
    @Path("list/{componentID}/{userID}")
    List<SubComponentList> getSubComponentListByComponentID(@PathParam("componentID")String componentID,@PathParam("userID")String userID);
    @GET
    @Path("{componentID}/{userID}")
    SubComponentList getSubComponentBySubComponentID(@PathParam("componentID")String subComponentID,@PathParam("userID")String userID);
    
}
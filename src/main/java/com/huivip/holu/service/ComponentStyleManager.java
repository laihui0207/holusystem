package com.huivip.holu.service;

import com.huivip.holu.model.ComponentStyle;
import com.huivip.holu.model.Mission;
import com.huivip.holu.model.User;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.apache.cxf.annotations.GZIP;

import javax.jws.WebService;
import javax.ws.rs.*;
import java.util.List;

@WebService
@GZIP
@Path("/componentStyles")
public interface ComponentStyleManager extends GenericManager<ComponentStyle, Long> {
    List<ComponentStyle> getComponentStypeListByCompany(String companyId);
    @GET
    @Path("processList/{companyID}/{styleID}/{userId}/{componentId}")
    List<ComponentStyle> getProcessListByCompanyAndStyleName(@PathParam("styleID") String styleID,
                                                             @PathParam("userId") String userId,
                                                             @PathParam("componentId") String componentID);
    List<ComponentStyle> getProcessListByCompanyAndStyleName(String styleID,
                                                             User user, String componentID, ExtendedPaginatedList list);
    ComponentStyle getComponentProcessByProcessID(String styleProcessID);
    @GET
    @Path("user/{userId}")
    List<ComponentStyle> myTask(@PathParam("userId") String userId);
    @GET
    @Path("task/{userId}/{projectID}")
    List<Mission> getMyTask(@PathParam("userId") String userId,
                            @PathParam("projectID") String projectID,
                            @DefaultValue("all") @QueryParam("type") String taskType);
    @GET
    @Path("task/{userID}")
    List<Mission> getTaskByComponentList(@PathParam("userID")String userID,
                                         @QueryParam("cplist")String componentList,
                                         @DefaultValue("all") @QueryParam("type") String taskType);

    @GET
    @Path("processList/{styleID}/{userId}/{componentId}")
    List<ComponentStyle> getProcessListBySubComponent(@PathParam("styleID") String styleID,
                                        @PathParam("userId") String userId,
                                        @PathParam("componentId") String subComponentID);
}
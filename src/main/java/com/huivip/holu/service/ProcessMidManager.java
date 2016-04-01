package com.huivip.holu.service;

import com.huivip.holu.model.LabelValue;
import com.huivip.holu.model.Mission;
import com.huivip.holu.model.ProcessMid;
import org.apache.cxf.annotations.GZIP;

import javax.jws.WebService;
import javax.ws.rs.*;
import java.util.List;

@WebService
@GZIP
@Path("/processMid")
public interface ProcessMidManager extends GenericManager<ProcessMid, Long> {
    ProcessMid save(ProcessMid object, String userID);
    @GET
    @Path("{userID}/{componentID}/{processID}")
    ProcessMid getProcessMid(@PathParam("componentID")String componentID,@PathParam("processID") String processID,@PathParam("userID") String userID);
    ProcessMid getProcessMid2(String componentID,String processID,String companyID);
    @GET
    @Path("projects/{userID}")
    List<LabelValue> getProjectOfUser(@PathParam("userID") String userID,@DefaultValue("all") @QueryParam("type") String taskType);
    @GET
    @Path("styles/{projectID}/{userID}")
    List<LabelValue> getComponentStyleOfProject(@PathParam("projectID") String projectID,
                                                @PathParam("userID") String userId,
                                                @DefaultValue("all") @QueryParam("type") String taskType);
    @GET
    @Path("missions/{projectID}/{styleID}/{userID}")
    List<Mission> getMyMissions(@PathParam("projectID") String projectID,
                                @PathParam("styleID") String styleID,
                                @PathParam("userID") String userID,
                                @QueryParam("type")String type,
                                @DefaultValue("0") @QueryParam("page") String pageIndex,
                                @DefaultValue("25") @QueryParam("pageSize") String pageSize);
    @GET
    @Path("subComponents/{projectID}/{styleID}/{userID}")
    List<Mission> getSumComponentsOfMyMissions(@PathParam("projectID") String projectID,
                                               @PathParam("styleID") String styleID,
                                               @PathParam("userID") String userID,
                                               @PathParam("subComponentId") String subComponentID,
                                               @QueryParam("type") String type);

    @POST
    @Path("confirm")
    ProcessMid save(@FormParam("subComponentID") String subComponentID,
                    @FormParam("styleProcessID") String styleProcessID,
                    @FormParam("processNote") String processNote,
                    @FormParam("startDate") String startDate,
                    @FormParam("endDate") String endDate,
                    @FormParam("positionGPS") String positionGPS,
                    @FormParam("positionName") String positionName,
                    @FormParam("userID") String userID);

    @POST
    @Path("BatchStartConfirm")
    List<ProcessMid> batchConfirm(@FormParam("Data") String Data,
                                  @FormParam("type")String type,
                                  @FormParam("positionGPS") String positionGPS,
                                  @FormParam("positionName") String positionname,
                                  @FormParam("userID") String userID);

    @POST
    @Path("startconfirm")
    ProcessMid startConfirm(@FormParam("subComponentID") String subComponentID,
                            @FormParam("styleProcessID") String styleProcessID,
                            @FormParam("processNote") String processNote,
                            @FormParam("startDate") String startDate,
                            @FormParam("endDate") String endDate,
                            @FormParam("positionGPS") String positionGPS,
                            @FormParam("positionName") String positionName,
                            @FormParam("userID") String userID);

    @POST
    @Path("endconfirm")
    ProcessMid stopConfirm(@FormParam("subComponentID") String subComponentID,
                           @FormParam("styleProcessID") String styleProcessID,
                           @FormParam("processNote") String processNote,
                           @FormParam("startDate") String startDate,
                           @FormParam("endDate") String endDate,
                           @FormParam("positionGPS") String positionGPS,
                           @FormParam("positionName") String positionName,
                           @FormParam("userID") String userID);

    @POST
    @Path("confirmquestion")
    ProcessMid ConfirmQuestion(@FormParam("subComponentID") String subComponentID,
                               @FormParam("styleProcessID") String styleProcessID,
                               @FormParam("processNote") String processNote,
                               @FormParam("startDate") String startDate,
                               @FormParam("endDate") String endDate,
                               @FormParam("positionGPS") String positionGPS,
                               @FormParam("positionName") String positionName,
                               @FormParam("userID") String userID);
}
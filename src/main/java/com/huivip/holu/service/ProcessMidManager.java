package com.huivip.holu.service;

import com.huivip.holu.model.ProcessMid;

import javax.jws.WebService;
import javax.ws.rs.*;

@WebService
@Path("/processMid")
public interface ProcessMidManager extends GenericManager<ProcessMid, Long> {
    ProcessMid save(ProcessMid object, String userID);
    @GET
    @Path("{userID}/{componentID}/{processID}")
    ProcessMid getProcessMid(@PathParam("componentID")String componentID,@PathParam("processID") String processID,@PathParam("userID") String userID);
    ProcessMid getProcessMid2(String componentID,String processID,String companyID);
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
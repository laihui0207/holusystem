package com.huivip.holu.service;

import com.huivip.holu.model.ProcessMid;

import javax.jws.WebService;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@WebService
@Path("/processMid")
public interface ProcessMidManager extends GenericManager<ProcessMid, Long> {
   ProcessMid save(ProcessMid object,String userID);

   ProcessMid getProcessMid(String componentID,String processID,String userID);
   @POST
   @Path("confirm")
   ProcessMid save(@FormParam("subComponentID")String subComponentID,
                   @FormParam("styleProcessID")String styleProcessID,
                   @FormParam("processNote")String processNote,
                   @FormParam("startDate")String startDate,
                   @FormParam("endDate")String endDate,
                   @FormParam("positionGPS")String positionGPS,
                   @FormParam("userID") String userID);
}
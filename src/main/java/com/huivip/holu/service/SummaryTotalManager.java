package com.huivip.holu.service;

import com.huivip.holu.model.SummaryItem;
import com.huivip.holu.model.SummaryProcess;
import com.huivip.holu.model.SummaryTotal;

import javax.jws.WebService;
import javax.ws.rs.*;
import java.util.HashMap;
import java.util.List;

@WebService
@Path("/Summary")
public interface SummaryTotalManager extends GenericManager<SummaryTotal, Long> {
    // ItemStyle: project, fatory,  startOrEnd: start,end
    @GET
    @Path("{userID}")
    List<SummaryItem> getHomePageSummary(@PathParam("userID")String userID,
                                         @DefaultValue("project") @QueryParam("itemStyle") String ItemStyle,
                                         @DefaultValue("today") @QueryParam("sumDate")String sumDate,
                                         @DefaultValue("end") @QueryParam("startorend")String startOrEnd);
    @GET
    @Path("{userID}/item")
    HashMap<String, List<SummaryItem>> getHomePageDetailSummary(@PathParam("userID") String userID,
                                                                @DefaultValue("project") @QueryParam("itemStyle") String ItemStyle,
                                                                @DefaultValue("today") @QueryParam("sumDate") String sumDate,
                                                                @DefaultValue("end") @QueryParam("startorend") String startOrEnd);
    @GET
    @Path("{userID}/Detail/{itemName}")
    HashMap<String, List<SummaryItem>> getSummaryDetail(@PathParam("userID") String userID, @PathParam("itemName") String itemName,
                                                        @DefaultValue("project")@QueryParam("itemStyle") String ItemStyle,
                                                        @DefaultValue("today") @QueryParam("sumDate") String sumDate,
                                                        @DefaultValue("end") @QueryParam("startorend") String startOrEnd);

    @GET
    @Path("{userID}/{itemStyle}/progress")
    List<SummaryProcess> getSummaryProcess(@PathParam("userID") String userID,@PathParam("itemStyle") String itemStyle);

    @GET
    @Path("{userID}/{itemStyle}/progress/{itemId}")
    HashMap<String,List<SummaryProcess>> getSummaryProcessDetail(@PathParam("userID") String userID,
                                                 @PathParam("itemStyle") String itemStyle,
                                                 @PathParam("itemId")String itemId);

}
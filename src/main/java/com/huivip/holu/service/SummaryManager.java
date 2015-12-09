package com.huivip.holu.service;

import com.huivip.holu.model.Summary;
import com.huivip.holu.model.SummaryItem;
import org.apache.cxf.annotations.GZIP;

import javax.jws.WebService;
import javax.ws.rs.*;
import java.util.List;

@WebService
@GZIP
@Path("/summaryDetail")
public interface SummaryManager extends GenericManager<Summary, Long> {
   @GET
   List<Summary> summaryList(String userId);
   @GET
   @Path("{userID}/{itemID}")
   List<SummaryItem> getSummaryDetail(@PathParam("userID")String userID,@PathParam("itemID")String itemID,
                                        @QueryParam("itemStyle") String ItemStyle,
                                        @DefaultValue("today") @QueryParam("sumDate")String sumDate,
                                        @DefaultValue("start") @QueryParam("startorend")String startOrEnd);

}
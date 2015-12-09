package com.huivip.holu.service;

import com.huivip.holu.model.Mission;
import com.huivip.holu.model.Task;
import org.apache.cxf.annotations.GZIP;

import javax.jws.WebService;
import javax.ws.rs.*;
import java.util.List;

@WebService
@GZIP
@Path("/tasks")
public interface TaskManager extends GenericManager<Task, Long> {
    @GET
    @Path("{userId}")
    List<Mission> getTaskOfUser(@PathParam("userId") String userId,
                                @DefaultValue("all") @QueryParam("type") String taskType,
                                @DefaultValue("0") @QueryParam("page") String page,
                                @DefaultValue("25") @QueryParam("pageSize") String pageSize);

    @GET
    @Path("subComponents/{userId}")
    List<String> getSubComponentOfTask(@PathParam("userId")String userId);
    @GET
    @Path("{userId}/{subId}")
    List<Mission> getTaskBySubComponent(@PathParam("userId") String userId,
                                        @PathParam("subId") String subComponentID,
                                       @DefaultValue("all") @QueryParam("type") String taskType);
}
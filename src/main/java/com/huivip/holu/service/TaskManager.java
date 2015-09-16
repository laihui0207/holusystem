package com.huivip.holu.service;

import com.huivip.holu.model.Task;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@WebService
@Path("/tasks")
public interface TaskManager extends GenericManager<Task, Long> {
    @GET
    @Path("{userId}")
    List<Task> getTaskOfUser(@PathParam("userId") String userId);
}
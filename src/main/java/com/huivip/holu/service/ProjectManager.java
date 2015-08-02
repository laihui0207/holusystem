package com.huivip.holu.service;

import com.huivip.holu.model.Project;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@WebService
@Path("/projects")
public interface ProjectManager extends GenericManager<Project, Long> {
    @GET
    @Path("user/{userID}")
    List<Project> getMyProject(@PathParam("userID")String userID);
    @GET
    @Path("user/{userID}/{parentID}")
    List<Project> getMySubProject(@PathParam("userID")String userID,@PathParam("parentID")String parentID);
    List<Project> getProjectByUserID(String userID,String parentProject,ExtendedPaginatedList list);
    @GET
    @Path("{projectID}")
    Project getProjectByprojectID(@PathParam("projectID")String projectID);


    
}
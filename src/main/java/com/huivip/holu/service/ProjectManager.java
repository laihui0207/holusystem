package com.huivip.holu.service;

import com.huivip.holu.model.Project;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@WebService
@Path("/projects")
public interface ProjectManager extends GenericManager<Project, Long> {
    @GET
    @Path("company/{companyId}")
    List<Project> getProjectListByCompany(@PathParam("companyId")String CompanyID);

    @GET
    @Path("user/{userId}")
    List<Project> getProjectListByUser(@PathParam("userId")String userId);
    
}
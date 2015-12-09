package com.huivip.holu.service;

import com.huivip.holu.model.Department;
import com.huivip.holu.model.SelectLabelValue;
import org.apache.cxf.annotations.GZIP;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@WebService
@GZIP
@Path("/departments")
public interface DepartmentManager extends GenericManager<Department, Long> {

    Department getDepartmentByDepartmentID(String departmentID);
    @Path("slv")
    @GET
    List<SelectLabelValue> getDepartmentLabelValue(@QueryParam("userID") String userID);
}
package com.huivip.holu.service;

import com.huivip.holu.model.Department;
import com.huivip.holu.model.SelectLabelValue;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@WebService
@Path("/departments")
public interface DepartmentManager extends GenericManager<Department, Long> {

    Department getDepartmentByDepartmentID(String departmentID);
    @Path("slv")
    @GET
    List<SelectLabelValue> getDepartmentLabelValue();
}
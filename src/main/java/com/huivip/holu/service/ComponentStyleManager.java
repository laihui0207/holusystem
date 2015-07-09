package com.huivip.holu.service;

import com.huivip.holu.model.ComponentStyle;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@WebService
@Path("/componentStyles")
public interface ComponentStyleManager extends GenericManager<ComponentStyle, Long> {
    List<ComponentStyle> getComponentStypeListByCompany(String companyId);
    @GET
    @Path("company/{companyId}/style/{styleName}")
    List<ComponentStyle> getProcessListByCompanyAndStyleName(@PathParam("styleName")String styleName,@PathParam("companyId")String companyId);
    
}
package com.huivip.holu.service;

import com.huivip.holu.model.ComponentStyle;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;

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
    @Path("processList/{companyID}/{styleID}/{userId}")
    List<ComponentStyle> getProcessListByCompanyAndStyleName(@PathParam("styleID")String styleID,
                                                             @PathParam("companyID")String companyId,
                                                             @PathParam("userId")String userId);
    List<ComponentStyle> getProcessListByCompanyAndStyleName(String styleID,
                                                             String companyId,
                                                             String userId,ExtendedPaginatedList list);
    ComponentStyle getComponentProcessByProcessID(String styleProcessID);
    
}
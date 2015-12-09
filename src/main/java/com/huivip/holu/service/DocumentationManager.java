package com.huivip.holu.service;

import com.huivip.holu.model.Documentation;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import org.apache.cxf.annotations.GZIP;

import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@WebService
@GZIP
@Path("/Documentations")
public interface DocumentationManager extends GenericManager<Documentation, Long> {
    @GET
    List<Documentation> getDocumentations(@DefaultValue("0") @QueryParam("page") String pageIndex,
                                          @DefaultValue("10") @QueryParam("pageSize") String pageSize,
                                          @DefaultValue("all") @QueryParam("type") String docType);

    @GET
    @Path("user/{userId}")
    List<Documentation> myDocumentations(@PathParam("userId")String userId,ExtendedPaginatedList list);

    @GET
    @Path("{id}/download/{userId}")
    Response downloadDocumentation(@PathParam("id") String id,@PathParam("userId")String userId);

    @GET
    @Path("{id}/view/{userId}")
    Documentation viewDocumentation(@PathParam("id") String id,@PathParam("userId")String userId);

}
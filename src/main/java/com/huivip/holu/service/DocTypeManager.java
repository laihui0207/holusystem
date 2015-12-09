package com.huivip.holu.service;

import com.huivip.holu.model.DocType;
import org.apache.cxf.annotations.GZIP;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@WebService
@GZIP
@Path("/doctypes")
public interface DocTypeManager extends GenericManager<DocType, Long> {
    @GET
    List<DocType> getDocTypes();
}
package com.huivip.holu.service;

import com.huivip.holu.model.DocType;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@WebService
@Path("/doctypes")
public interface DocTypeManager extends GenericManager<DocType, Long> {
    @GET
    List<DocType> getDocTypes();
}
package com.huivip.holu.service;

import com.huivip.holu.model.ClientVersion;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@WebService
@Path("/client")
public interface ClientVersionManager extends GenericManager<ClientVersion, Long> {

    ClientVersion getClientByVersion(String version);
    ClientVersion getLastedClient();

    @GET
    @Path("{version}/download")
    Response downloadClient(@PathParam("version") String version);

}
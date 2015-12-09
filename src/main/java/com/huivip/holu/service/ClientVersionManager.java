package com.huivip.holu.service;

import com.huivip.holu.model.ClientVersion;
import org.apache.cxf.annotations.GZIP;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@WebService
@GZIP
@Path("/client")
public interface ClientVersionManager extends GenericManager<ClientVersion, Long> {

    ClientVersion getClientByVersion(String version);
    ClientVersion getLastedClient();

    @GET
    @Path("{version}/download")
    Response downloadClient(@PathParam("version") String version);

    String getLastQRFile();

    @GET
    @Path("version")
    ClientVersion getLastVersion();

    @GET
    @Path("downloadlink")
    String getDownloadLink();

}
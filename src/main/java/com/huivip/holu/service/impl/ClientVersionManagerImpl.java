package com.huivip.holu.service.impl;

import com.huivip.holu.dao.ClientVersionDao;
import com.huivip.holu.model.ClientVersion;
import com.huivip.holu.service.ClientVersionManager;
import com.huivip.holu.util.SteelConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.io.File;
import java.util.Date;

@Service("clientVersionManager")
@WebService(serviceName = "ClientVersionService", endpointInterface = "com.huivip.holu.service.ClientVersionManager")
public class ClientVersionManagerImpl extends GenericManagerImpl<ClientVersion, Long> implements ClientVersionManager {
    private static final Log log = LogFactory.getLog(ClientVersionManagerImpl.class);
    ClientVersionDao clientVersionDao;

    @Autowired
    public ClientVersionManagerImpl(ClientVersionDao clientVersionDao) {
        super(clientVersionDao);
        this.clientVersionDao = clientVersionDao;
    }

    @Override
    public ClientVersion getClientByVersion(String version) {
        return clientVersionDao.getClientByVersion(version);
    }

    @Override
    public ClientVersion getLastedClient() {
        return clientVersionDao.getLastedClient();
    }

    @Override
    public Response downloadClient(String version) {
        ClientVersion clientVersion=null;
        if(version!=null && version.equalsIgnoreCase("lasted")){
            clientVersion=getLastedClient();
        }
        else {
            clientVersion=getClientByVersion(version);
        }
        String configureUploadDir= SteelConfig.getConfigure(SteelConfig.ClientDirectory);
        clientVersion.setDownloadTimes(clientVersion.getDownloadTimes()+1);
        clientVersion.setLastDownloadTime(new Date());
        clientVersionDao.save(clientVersion);
        String uploadDir="";
        if(null!=configureUploadDir && configureUploadDir.length()>0){
            uploadDir=configureUploadDir;
        }
        File file=new File(uploadDir+clientVersion.getStorePath());
        ResponseBuilder response = Response.ok((Object)file, MediaType.APPLICATION_OCTET_STREAM);
        response.header("Content-Disposition", "attachment; filename=ICMS2015.apk");
        return response.build();
    }

    @Override
    public String getLastQRFile() {
        ClientVersion client=getLastedClient();
        if(client!=null) return client.getQRCode();
        return null;
    }

    @Override
    public ClientVersion getLastVersion() {
        ClientVersion clientVersion=getLastedClient();
        /*String configureUploadDir= SteelConfig.getConfigure(SteelConfig.ClientDirectory);
        String uploadDir="";
        if(null!=configureUploadDir && configureUploadDir.length()>0){
            uploadDir=configureUploadDir;
        }
        File file=new File(uploadDir+clientVersion.getStorePath());
        clientVersion.setClientSize(file.length());*/
        log.info("Get last Client Version:"+clientVersion.getVersion());
        return clientVersion;
    }

    @Override
    public String getDownloadLink() {
        throw new WebApplicationException("error");
    }
}
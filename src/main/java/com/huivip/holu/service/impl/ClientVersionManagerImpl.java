package com.huivip.holu.service.impl;

import com.huivip.holu.dao.ClientVersionDao;
import com.huivip.holu.model.ClientVersion;
import com.huivip.holu.model.Documentation;
import com.huivip.holu.service.ClientVersionManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import com.huivip.holu.util.SteelConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import javax.jws.WebService;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service("clientVersionManager")
@WebService(serviceName = "ClientVersionService", endpointInterface = "com.huivip.holu.service.ClientVersionManager")
public class ClientVersionManagerImpl extends GenericManagerImpl<ClientVersion, Long> implements ClientVersionManager {
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

        String uploadDir="";
        if(null!=configureUploadDir && configureUploadDir.length()>0){
            uploadDir=configureUploadDir;
        }
        File file=new File(uploadDir+clientVersion.getStorePath());
        Response.ResponseBuilder response = Response.ok(file, MediaType.APPLICATION_OCTET_STREAM);
        response.header("Content-Disposition", "attachment; filename=\"ICMS2015.apk\"");
        return response.build();
    }

    @Override
    public String getLastQRFile() {
        ClientVersion client=getLastedClient();
        if(client!=null) return client.getQRCode();
        return null;
    }

    @Override
    public String getLastVersion() {
        ClientVersion clientVersion=getLastedClient();
        return clientVersion.getVersion();
    }

    @Override
    public String getDownloadLink() {
        return null;
    }
}
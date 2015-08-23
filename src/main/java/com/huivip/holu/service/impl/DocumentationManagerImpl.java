package com.huivip.holu.service.impl;

import com.huivip.holu.dao.DocumentationDao;
import com.huivip.holu.model.Documentation;
import com.huivip.holu.service.DocumentationManager;
import com.huivip.holu.util.SteelConfig;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import com.huivip.holu.webapp.helper.PaginatedListImpl;
import com.huivip.holu.webapp.util.ApplicationContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.List;

@Service("documentationManager")
@WebService(serviceName = "DocumentationService", endpointInterface = "com.huivip.holu.service.DocumentationManager")
public class DocumentationManagerImpl extends GenericManagerImpl<Documentation, Long> implements DocumentationManager {
    DocumentationDao documentationDao;

    @Autowired
    public DocumentationManagerImpl(DocumentationDao documentationDao) {
        super(documentationDao);
        this.documentationDao = documentationDao;
    }

    @Override
    public List<Documentation> getDocumentations(String pageIndex,String pageSize,String docType) {
        ExtendedPaginatedList list=new PaginatedListImpl();
        list.setIndex(Integer.parseInt(pageIndex));
        list.setPageSize(Integer.parseInt(pageSize));
        if(null!=docType && docType.equalsIgnoreCase("all")){
            documentationDao.getAllPagable(list);
        }
        else {
            documentationDao.listDocumentatonsByDocType(docType,list);
        }
        return list.getList();
    }

    @Override
    public List<Documentation> myDocumentations(String userId,ExtendedPaginatedList list) {
        return documentationDao.myDocumentations(userId,list);
    }

    @Override
    public Response downloadDocumentation(String id,String userId) {

        Documentation documentation=documentationDao.get(Long.parseLong(id));
        String configureUploadDir= SteelConfig.getConfigure(SteelConfig.DocumentManagerDirectory);

        String uploadDir="";
        if(null!=configureUploadDir && configureUploadDir.length()>0){
            uploadDir=configureUploadDir;
        }
        File file=new File(uploadDir+documentation.getStorePath());
        Response.ResponseBuilder response = Response.ok(file, MediaType.APPLICATION_OCTET_STREAM);
        response.header("Content-Disposition", "attachment; filename=\"" + documentation.getFileName() + "\"");
        return response.build();
    }
}
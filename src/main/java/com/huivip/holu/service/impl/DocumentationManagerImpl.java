package com.huivip.holu.service.impl;

import com.huivip.holu.dao.DocumentationDao;
import com.huivip.holu.model.Documentation;
import com.huivip.holu.service.DocumentationManager;
import com.huivip.holu.util.SteelConfig;
import com.huivip.holu.util.cache.Cache2kProvider;
import com.huivip.holu.webapp.helper.ExtendedPaginatedList;
import com.huivip.holu.webapp.helper.PaginatedListImpl;
import org.cache2k.Cache;
import org.cache2k.CacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.List;

@Service("documentationManager")
@WebService(serviceName = "DocumentationService", endpointInterface = "com.huivip.holu.service.DocumentationManager")
public class DocumentationManagerImpl extends GenericManagerImpl<Documentation, Long> implements DocumentationManager {
    DocumentationDao documentationDao;
    Cache<String,Documentation> cache=null;

    @Autowired
    public DocumentationManagerImpl(DocumentationDao documentationDao) {
        super(documentationDao);
        this.documentationDao = documentationDao;
        cache= Cache2kProvider.getinstance().setCache(Documentation.class,
                CacheBuilder.newCache(String.class,Documentation.class).build());
    }

    @Override
    public List<Documentation> getDocumentations(String pageIndex,String pageSize,String docType) {
        ExtendedPaginatedList list=new PaginatedListImpl();
        list.setIndex(Integer.parseInt(pageIndex));
        list.setPageSize(Integer.parseInt(pageSize));
        String cacheKey=Documentation.LIST_CACHE+docType+"_"+pageIndex+"_"+pageSize;
        maintainCacheKey(Documentation.LIST_NEWS_CACHE_KEY,cacheKey);
        ExtendedPaginatedList listFromCache=listCache.peek(cacheKey);
        if(listFromCache==null){
            if(null!=docType && docType.equalsIgnoreCase("all")){
                documentationDao.getAllPagable(list);
            }
            else {
                documentationDao.listDocumentatonsByDocType(docType,list);
            }
            listCache.put(cacheKey,list);
        }
        else {
            list=listFromCache;
        }
        return list.getList();
    }

    @Override
    public List<Documentation> myDocumentations(String userId,ExtendedPaginatedList list) {
        return documentationDao.myDocumentations(userId,list);
    }

    @Override
    public Response downloadDocumentation(String id,String userId) {

        Documentation documentation=cache.peek(id);
        if(documentation==null){
            documentation=documentationDao.get(Long.parseLong(id));
            cache.put(id,documentation);
        }
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

    @Override
    public Documentation viewDocumentation(String id, String userId) {
        Documentation documentation=cache.peek(id);
        if(documentation==null){
            documentation=documentationDao.get(Long.parseLong(id));
            cache.put(id,documentation);
        }
        return documentation;
    }
}
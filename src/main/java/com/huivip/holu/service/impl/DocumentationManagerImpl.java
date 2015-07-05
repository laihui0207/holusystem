package com.huivip.holu.service.impl;

import com.huivip.holu.dao.DocumentationDao;
import com.huivip.holu.model.Documentation;
import com.huivip.holu.service.DocumentationManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

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
    public List<Documentation> getDocumentations() {
        return documentationDao.getAll();
    }

    @Override
    public Documentation downloadDocumentation(String id) {
        return null;
    }
}
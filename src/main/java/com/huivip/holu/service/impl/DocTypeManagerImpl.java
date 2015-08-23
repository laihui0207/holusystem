package com.huivip.holu.service.impl;

import com.huivip.holu.dao.DocTypeDao;
import com.huivip.holu.model.DocType;
import com.huivip.holu.service.DocTypeManager;
import com.huivip.holu.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("docTypeManager")
@WebService(serviceName = "DocTypeService", endpointInterface = "com.huivip.holu.service.DocTypeManager")
public class DocTypeManagerImpl extends GenericManagerImpl<DocType, Long> implements DocTypeManager {
    DocTypeDao docTypeDao;

    @Autowired
    public DocTypeManagerImpl(DocTypeDao docTypeDao) {
        super(docTypeDao);
        this.docTypeDao = docTypeDao;
    }

    @Override
    public List<DocType> getDocTypes() {
        return docTypeDao.getAll();
    }
}
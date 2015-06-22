package com.huivip.holu.dao;

import com.huivip.holu.dao.BaseDaoTestCase;
import com.huivip.holu.model.DocType;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DocTypeDaoTest extends BaseDaoTestCase {
    @Autowired
    private DocTypeDao docTypeDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveDocType() {
        DocType docType = new DocType();

        // enter all required fields

        log.debug("adding docType...");
        docType = docTypeDao.save(docType);

        docType = docTypeDao.get(docType.getId());

        assertNotNull(docType.getId());

        log.debug("removing docType...");

        docTypeDao.remove(docType.getId());

        // should throw DataAccessException 
        docTypeDao.get(docType.getId());
    }
}
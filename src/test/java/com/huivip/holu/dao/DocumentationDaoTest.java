package com.huivip.holu.dao;

import com.huivip.holu.dao.BaseDaoTestCase;
import com.huivip.holu.model.Documentation;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DocumentationDaoTest extends BaseDaoTestCase {
    @Autowired
    private DocumentationDao documentationDao;

    @Test(expected=DataAccessException.class)
    public void testAddAndRemoveDocumentation() {
        Documentation documentation = new Documentation();

        // enter all required fields
        documentation.setDocSize(1424348037);
        documentation.setViewLimit(Boolean.FALSE);

        log.debug("adding documentation...");
        documentation = documentationDao.save(documentation);

        documentation = documentationDao.get(documentation.getId());

        assertNotNull(documentation.getId());

        log.debug("removing documentation...");

        documentationDao.remove(documentation.getId());

        // should throw DataAccessException 
        documentationDao.get(documentation.getId());
    }
}
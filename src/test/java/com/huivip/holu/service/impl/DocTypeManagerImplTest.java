package com.huivip.holu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.huivip.holu.dao.DocTypeDao;
import com.huivip.holu.model.DocType;
import com.huivip.holu.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class DocTypeManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private DocTypeManagerImpl manager;

    @Mock
    private DocTypeDao dao;

    @Test
    public void testGetDocType() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final DocType docType = new DocType();
        given(dao.get(id)).willReturn(docType);

        //when
        DocType result = manager.get(id);

        //then
        assertSame(docType, result);
    }

    @Test
    public void testGetDocTypes() {
        log.debug("testing getAll...");
        //given
        final List<DocType> docTypes = new ArrayList<>();
        given(dao.getAll()).willReturn(docTypes);

        //when
        List result = manager.getAll();

        //then
        assertSame(docTypes, result);
    }

    @Test
    public void testSaveDocType() {
        log.debug("testing save...");

        //given
        final DocType docType = new DocType();
        // enter all required fields

        given(dao.save(docType)).willReturn(docType);

        //when
        manager.save(docType);

        //then
        verify(dao).save(docType);
    }

    @Test
    public void testRemoveDocType() {
        log.debug("testing remove...");

        //given
        final Long id = -11L;
        willDoNothing().given(dao).remove(id);

        //when
        manager.remove(id);

        //then
        verify(dao).remove(id);
    }
}

package com.huivip.holu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.huivip.holu.dao.DocumentationDao;
import com.huivip.holu.model.Documentation;
import com.huivip.holu.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class DocumentationManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private DocumentationManagerImpl manager;

    @Mock
    private DocumentationDao dao;

    @Test
    public void testGetDocumentation() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final Documentation documentation = new Documentation();
        given(dao.get(id)).willReturn(documentation);

        //when
        Documentation result = manager.get(id);

        //then
        assertSame(documentation, result);
    }

    @Test
    public void testGetDocumentations() {
        log.debug("testing getAll...");
        //given
        final List<Documentation> documentations = new ArrayList<>();
        given(dao.getAll()).willReturn(documentations);

        //when
        List result = manager.getAll();

        //then
        assertSame(documentations, result);
    }

    @Test
    public void testSaveDocumentation() {
        log.debug("testing save...");

        //given
        final Documentation documentation = new Documentation();
        // enter all required fields
        documentation.setDocSize(1308076822);
        documentation.setViewLimit(Boolean.FALSE);

        given(dao.save(documentation)).willReturn(documentation);

        //when
        manager.save(documentation);

        //then
        verify(dao).save(documentation);
    }

    @Test
    public void testRemoveDocumentation() {
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

package com.huivip.holu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.huivip.holu.dao.CompanyDatabaseIndexDao;
import com.huivip.holu.model.CompanyDatabaseIndex;
import com.huivip.holu.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class CompanyDatabaseIndexManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private CompanyDatabaseIndexManagerImpl manager;

    @Mock
    private CompanyDatabaseIndexDao dao;

    @Test
    public void testGetCompanyDatabaseIndex() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final CompanyDatabaseIndex companyDatabaseIndex = new CompanyDatabaseIndex();
        given(dao.get(id)).willReturn(companyDatabaseIndex);

        //when
        CompanyDatabaseIndex result = manager.get(id);

        //then
        assertSame(companyDatabaseIndex, result);
    }

    @Test
    public void testGetCompanyDatabaseIndices() {
        log.debug("testing getAll...");
        //given
        final List<CompanyDatabaseIndex> companyDatabaseIndices = new ArrayList<>();
        given(dao.getAll()).willReturn(companyDatabaseIndices);

        //when
        List result = manager.getAll();

        //then
        assertSame(companyDatabaseIndices, result);
    }

    @Test
    public void testSaveCompanyDatabaseIndex() {
        log.debug("testing save...");

        //given
        final CompanyDatabaseIndex companyDatabaseIndex = new CompanyDatabaseIndex();
        // enter all required fields

        given(dao.save(companyDatabaseIndex)).willReturn(companyDatabaseIndex);

        //when
        manager.save(companyDatabaseIndex);

        //then
        verify(dao).save(companyDatabaseIndex);
    }

    @Test
    public void testRemoveCompanyDatabaseIndex() {
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

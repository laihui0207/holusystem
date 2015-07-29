package com.huivip.holu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.huivip.holu.dao.CompanyDao;
import com.huivip.holu.model.Company;
import com.huivip.holu.service.impl.BaseManagerMockTestCase;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

public class CompanyManagerImplTest extends BaseManagerMockTestCase {

    @InjectMocks
    private CompanyManagerImpl manager;

    @Mock
    private CompanyDao dao;

    @Test
    public void testGetCompany() {
        log.debug("testing get...");
        //given
        final Long id = 7L;
        final Company company = new Company();
        given(dao.get(id)).willReturn(company);

        //when
        Company result = manager.get(id);

        //then
        assertSame(company, result);
    }

    @Test
    public void testGetCompanies() {
        log.debug("testing getAll...");
        //given
        final List<Company> companies = new ArrayList<>();
        given(dao.getAll()).willReturn(companies);

        //when
        List result = manager.getAll();

        //then
        assertSame(companies, result);
    }

    @Test
    public void testSaveCompany() {
        log.debug("testing save...");

        //given
        final Company company = new Company();
        // enter all required fields

        given(dao.save(company)).willReturn(company);

        //when
        manager.save(company);

        //then
        verify(dao).save(company);
    }

    @Test
    public void testRemoveCompany() {
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

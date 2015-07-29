package com.huivip.holu.webapp.controller;

import com.huivip.holu.service.CompanyDatabaseIndexManager;
import com.huivip.holu.model.CompanyDatabaseIndex;

import com.huivip.holu.webapp.controller.BaseControllerTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CompanyDatabaseIndexControllerTest extends BaseControllerTestCase {
    @Autowired
    private CompanyDatabaseIndexManager companyDatabaseIndexManager;
    @Autowired
    private CompanyDatabaseIndexController controller;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();
    }

    @Test
    public void testHandleRequest() throws Exception {
        mockMvc.perform(get("/companyDatabaseIndices"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("companyDatabaseIndexList"))
            .andExpect(view().name("companyDatabaseIndices"));
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        companyDatabaseIndexManager.reindex();

        Map<String,Object> model = mockMvc.perform((get("/companyDatabaseIndices")).param("q", "*"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("companyDatabaseIndexList"))
            .andReturn()
            .getModelAndView()
            .getModel();

        List results = (List) model.get("companyDatabaseIndexList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}

package com.huivip.holu.webapp.controller;

import com.huivip.holu.service.DocumentationManager;
import com.huivip.holu.model.Documentation;

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

public class DocumentationControllerTest extends BaseControllerTestCase {
    @Autowired
    private DocumentationManager documentationManager;
    @Autowired
    private DocumentationController controller;

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
        mockMvc.perform(get("/documentations"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("documentationList"))
            .andExpect(view().name("documentations"));
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        documentationManager.reindex();

        Map<String,Object> model = mockMvc.perform((get("/documentations")).param("q", "*"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("documentationList"))
            .andReturn()
            .getModelAndView()
            .getModel();

        List results = (List) model.get("documentationList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}

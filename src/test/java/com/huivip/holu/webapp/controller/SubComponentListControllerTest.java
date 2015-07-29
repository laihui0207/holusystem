package com.huivip.holu.webapp.controller;

import com.huivip.holu.service.SubComponentListManager;
import com.huivip.holu.model.SubComponentList;

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

public class SubComponentListControllerTest extends BaseControllerTestCase {
    @Autowired
    private SubComponentListManager subComponentListManager;
    @Autowired
    private SubComponentListController controller;

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
        mockMvc.perform(get("/subComponentLists"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("subComponentListList"))
            .andExpect(view().name("subComponentLists"));
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        subComponentListManager.reindex();

        Map<String,Object> model = mockMvc.perform((get("/subComponentLists")).param("q", "*"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("subComponentListList"))
            .andReturn()
            .getModelAndView()
            .getModel();

        List results = (List) model.get("subComponentListList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}

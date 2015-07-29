package com.huivip.holu.webapp.controller;

import com.huivip.holu.service.ComponentManager;
import com.huivip.holu.model.Component;

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

public class ComponentControllerTest extends BaseControllerTestCase {
    @Autowired
    private ComponentManager componentManager;
    @Autowired
    private ComponentController controller;

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
        mockMvc.perform(get("/components"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("componentList"))
            .andExpect(view().name("components"));
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        componentManager.reindex();

        Map<String,Object> model = mockMvc.perform((get("/components")).param("q", "*"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("componentList"))
            .andReturn()
            .getModelAndView()
            .getModel();

        List results = (List) model.get("componentList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}

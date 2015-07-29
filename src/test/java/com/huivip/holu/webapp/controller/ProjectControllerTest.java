package com.huivip.holu.webapp.controller;

import com.huivip.holu.service.ProjectManager;
import com.huivip.holu.model.Project;

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

public class ProjectControllerTest extends BaseControllerTestCase {
    @Autowired
    private ProjectManager projectManager;
    @Autowired
    private ProjectController controller;

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
        mockMvc.perform(get("/projects"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("projectList"))
            .andExpect(view().name("projects"));
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        projectManager.reindex();

        Map<String,Object> model = mockMvc.perform((get("/projects")).param("q", "*"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("projectList"))
            .andReturn()
            .getModelAndView()
            .getModel();

        List results = (List) model.get("projectList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}

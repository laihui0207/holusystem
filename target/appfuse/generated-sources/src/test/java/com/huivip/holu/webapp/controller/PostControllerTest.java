package com.huivip.holu.webapp.controller;

import com.huivip.holu.service.PostManager;
import com.huivip.holu.model.Post;

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

public class PostControllerTest extends BaseControllerTestCase {
    @Autowired
    private PostManager postManager;
    @Autowired
    private PostController controller;

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
        mockMvc.perform(get("/posts"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("postList"))
            .andExpect(view().name("posts"));
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        postManager.reindex();

        Map<String,Object> model = mockMvc.perform((get("/posts")).param("q", "*"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("postList"))
            .andReturn()
            .getModelAndView()
            .getModel();

        List results = (List) model.get("postList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}

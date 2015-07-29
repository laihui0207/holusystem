package com.huivip.holu.webapp.controller;

import com.huivip.holu.service.NewsManager;
import com.huivip.holu.model.News;

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

public class NewsControllerTest extends BaseControllerTestCase {
    @Autowired
    private NewsManager newsManager;
    @Autowired
    private NewsController controller;

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
        mockMvc.perform(get("/newss"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("newsList"))
            .andExpect(view().name("newss"));
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        newsManager.reindex();

        Map<String,Object> model = mockMvc.perform((get("/newss")).param("q", "*"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("newsList"))
            .andReturn()
            .getModelAndView()
            .getModel();

        List results = (List) model.get("newsList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}

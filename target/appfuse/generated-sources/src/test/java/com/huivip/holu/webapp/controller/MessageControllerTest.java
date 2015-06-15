package com.huivip.holu.webapp.controller;

import com.huivip.holu.service.MessageManager;
import com.huivip.holu.model.Message;

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

public class MessageControllerTest extends BaseControllerTestCase {
    @Autowired
    private MessageManager messageManager;
    @Autowired
    private MessageController controller;

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
        mockMvc.perform(get("/messages"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("messageList"))
            .andExpect(view().name("messages"));
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        messageManager.reindex();

        Map<String,Object> model = mockMvc.perform((get("/messages")).param("q", "*"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("messageList"))
            .andReturn()
            .getModelAndView()
            .getModel();

        List results = (List) model.get("messageList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}

package com.example.cicddemo.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Hello Test")
    @Test
    public void testHomeEndpoint() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Controller For CI/CD Pipeline"));
    }

    @DisplayName("Health Test")
    @Test
    public void testHealthEndpoint() throws Exception{
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("The Application Is Working Properly"));
    }


}

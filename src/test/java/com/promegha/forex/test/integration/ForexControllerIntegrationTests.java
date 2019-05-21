package com.promegha.forex.test.integration;

import com.promegha.forex.ForexController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ForexControllerIntegrationTests {
    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    private ForexController forexController;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.forexController).build();// Standalone context
    }

    @Test
    public void testEurToInr() throws Exception {
        mockMvc.perform(get("/forex/EUR/to/INR").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("exchangeRate", is(78.13762642527733)));
    }

    @Test
    public void testUsdToInr() throws Exception {
        mockMvc.perform(get("/forex/USD/to/INR").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("exchangeRate", is(69.7551)));
    }

    @Test
    public void testInvalidToCurrency() throws Exception {
        mockMvc.perform(get("/forex/EUR/to/ABC").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}

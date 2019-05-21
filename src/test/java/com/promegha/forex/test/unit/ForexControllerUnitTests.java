package com.promegha.forex.test.unit;

import com.promegha.forex.ExchangeValue;
import com.promegha.forex.ForexController;
import com.promegha.forex.ForexConversionException;
import com.promegha.forex.ForexService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ForexControllerUnitTests {
    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    private ForexController forexController;

    @MockBean
    ForexService forexService;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.forexController).build();// Standalone context
    }

    @Test
    public void testEurToInr() throws Exception {
        // Mocking service
        when(forexService.getCurrencyExchangeValue(eq("EUR"), eq("INR"))).thenReturn(
                new ExchangeValue("EUR", "INR", 78.13762642527733)
        );

        mockMvc.perform(get("/forex/EUR/to/INR").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("exchangeRate", is(78.13762642527733)));
    }

    @Test
    public void testUsdToInr() throws Exception {
        // Mocking service
        when(forexService.getCurrencyExchangeValue(eq("USD"), eq("INR"))).thenReturn(
                new ExchangeValue("USD", "INR", 69.7551)
        );

        mockMvc.perform(get("/forex/USD/to/INR").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("exchangeRate", is(69.7551)));
    }

    @Test
    public void testInvalidToCurrency() throws Exception {
        // Mocking service
        when(forexService.getCurrencyExchangeValue(eq("EUR"), eq("ABC"))).thenThrow(
            new ForexConversionException("Invalid TO currency")
        );

        mockMvc.perform(get("/forex/EUR/to/ABC").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}

package com.stockAssigment.stockAssigment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetDailyOpenClose() throws Exception {
        mockMvc.perform(get("/api/stocks/daily-open-close/AAPL/2023-02-09"))
                .andExpect(status().isOk());
    }
    @Test
    void testGetBatchQuotes() throws Exception {
        mockMvc.perform(get("/api/stocks/batch?symbols=AAPL,GOOG,MSFT"))
                .andExpect(status().isOk());
    }
}

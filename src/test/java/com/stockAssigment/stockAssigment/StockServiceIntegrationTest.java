package com.stockAssigment.stockAssigment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class StockServiceIntegrationTest {
    @Autowired
    private StockService stockService;

    @Test
    void testGetDailyOpenClose() {
        StockQuote quote = stockService.getDailyOpenClose("AAPL","2024-10-16");
        assertNotNull(quote,"The Stock quote should not be null");

    }
    @Test
    void testGetBatchQuotes() {
        List<StockQuote> quotes = stockService.getBatchQuotes(List.of("AAPL", "GOOG", "MSFT"));
        assertNotNull(quotes, "The list of stock quotes should not be null");
    }

}

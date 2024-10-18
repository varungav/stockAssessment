package com.stockAssigment.stockAssigment;

import com.stockAssigment.stockAssigment.Entity.StockQuote;
import com.stockAssigment.stockAssigment.dto.StockQuoteDTO;
import com.stockAssigment.stockAssigment.dto.StockQuoteMapper;
import com.stockAssigment.stockAssigment.service.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class StockServiceIntegrationTest {
    @Autowired
    private StockService stockService;

    @Autowired
    private StockQuoteMapper stockQuoteMapper;

    @Test
    void testGetDailyOpenClose() {
        StockQuote stockQuote = stockService.getDailyOpenClose("GOOG", "2023-02-08");
        StockQuoteDTO stockQuoteDTO = stockQuoteMapper.toDTO(stockQuote);

        assertEquals("GOOG", stockQuoteDTO.getSymbol());
        assertNotNull(stockQuoteDTO.getPrice());
        assertNotNull(stockQuoteDTO.getHighest());
        assertNotNull(stockQuoteDTO.getLowest());
        assertNotNull(stockQuoteDTO.getTimeStamp());
    }

    @Test
    void testGetBatchQuotes() {
        List<StockQuote> stockQuotes = stockService.getBatchQuotes(Arrays.asList("GOOG", "AAPL"));
        List<StockQuoteDTO> stockQuoteDTOs = stockQuotes.stream()
                .map(stockQuoteMapper::toDTO)
                .toList();

        assertEquals(2, stockQuoteDTOs.size());
        assertEquals("GOOG", stockQuoteDTOs.get(0).getSymbol());
        assertEquals("AAPL", stockQuoteDTOs.get(1).getSymbol());
    }

}

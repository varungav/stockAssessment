package com.stockAssigment.stockAssigment;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StockQuoteTest {
    @Test
    void testGettersAndSetters() {
        StockQuote stockQuote = new StockQuote();
        stockQuote.setId(1L);
        stockQuote.setSymbol("AAPL");
        stockQuote.setOpen(BigDecimal.valueOf(145.00));
        stockQuote.setClose(BigDecimal.valueOf(150.00));
        stockQuote.setHigh(BigDecimal.valueOf(152.00));
        stockQuote.setLow(BigDecimal.valueOf(144.00));
        stockQuote.setVolume(10000L);
        stockQuote.setTimestamp(new Date());


        assertEquals(1L,stockQuote.getId());
        assertEquals("AAPL",stockQuote.getSymbol());
        assertEquals(BigDecimal.valueOf(145.00),stockQuote.getOpen());
        assertEquals(BigDecimal.valueOf(150.00),stockQuote.getClose());
        assertEquals(BigDecimal.valueOf(152.00),stockQuote.getHigh());
        assertEquals(BigDecimal.valueOf(144.00),stockQuote.getLow());
        assertEquals(10000L,stockQuote.getVolume());
        assertNotNull(stockQuote.getTimestamp());
    }

}

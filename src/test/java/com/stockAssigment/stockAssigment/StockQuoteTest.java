package com.stockAssigment.stockAssigment;

import com.stockAssigment.stockAssigment.Entity.StockQuote;
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
        stockQuote.setHigh(BigDecimal.valueOf(150.75));
        stockQuote.setLow(BigDecimal.valueOf(145.50));
        stockQuote.setPrice(BigDecimal.valueOf(149.00));
        stockQuote.setPriceChange(BigDecimal.valueOf(3.50));
        stockQuote.setPercentageChange(BigDecimal.valueOf(2.40));
        stockQuote.setTimestamp(new Date());


        assertEquals(1L, stockQuote.getId());
        assertEquals("AAPL", stockQuote.getSymbol());
        assertEquals(BigDecimal.valueOf(150.75), stockQuote.getHigh());
        assertEquals(BigDecimal.valueOf(145.50), stockQuote.getLow());
        assertEquals(BigDecimal.valueOf(149.00), stockQuote.getPrice());
        assertEquals(BigDecimal.valueOf(3.50), stockQuote.getPriceChange());
        assertEquals(BigDecimal.valueOf(2.40), stockQuote.getPercentageChange());
        assertNotNull(stockQuote.getTimestamp());
    }

}

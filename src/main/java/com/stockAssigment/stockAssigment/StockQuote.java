package com.stockAssigment.stockAssigment;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class StockQuote {
    private String symbol;               // The ticker symbol of the stock
    private BigDecimal price;            // Current price of the stock
    private BigDecimal change;           // Price change from the previous close
    private BigDecimal percentageChange;  // Percentage change from the previous close
    private LocalDateTime timestamp;     // The timestamp of when the quote was taken
}

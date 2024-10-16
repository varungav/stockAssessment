package com.stockAssigment.stockAssigment;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StockQuote {
    private String symbol;            // Stock symbol (e.g., "MSFT")
    private BigDecimal open;          // Opening price
    private BigDecimal high;          // Highest price
    private BigDecimal low;           // Lowest price
    private BigDecimal close;         // Closing price
    private long volume;              // Trading volume
    private BigDecimal afterHours;    // After-hours price
    private BigDecimal preMarket;     // Pre-market price
    private String from;              // Date of the quote
    private BigDecimal price;         // Latest price (could use close price)
    private BigDecimal change;        // Change in price (close - open)
    private BigDecimal percentageChange; // Percentage change
}

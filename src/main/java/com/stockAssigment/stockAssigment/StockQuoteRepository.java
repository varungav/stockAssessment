package com.stockAssigment.stockAssigment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface StockQuoteRepository extends JpaRepository<StockQuote, Long> {
    StockQuote findBySymbolAndTimestamp(String symbol, Date timestamp);
}
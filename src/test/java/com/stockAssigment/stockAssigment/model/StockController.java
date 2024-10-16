package com.stockAssigment.stockAssigment.model;

import com.stockAssigment.stockAssigment.StockQuote;
import com.stockAssigment.stockAssigment.StockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/daily-open-close/{stocksTicker}/{date}")
    public StockQuote getDailyOpenClose(
            @PathVariable String stocksTicker,
            @PathVariable String date,
            @RequestParam(defaultValue = "true") boolean adjusted) {
        return stockService.getDailyOpenClose(stocksTicker, date);
    }

    // Endpoint to get quote by symbol
    @GetMapping("/quote/{symbol}")
    public StockQuote getQuoteBySymbol(@PathVariable String symbol) {
        return stockService.getQuoteBySymbol(symbol);
    }

    // Endpoint to get batch quotes by symbols
    @GetMapping("/batch-quotes")
    public List<StockQuote> getBatchQuotesBySymbols(@RequestParam List<String> symbols) {
        return stockService.getBatchQuotesBySymbols(symbols);
    }
}

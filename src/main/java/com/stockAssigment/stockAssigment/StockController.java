package com.stockAssigment.stockAssigment;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return stockService.getDailyOpenClose(stocksTicker, date); // Adjusted flag not used
    }

    @GetMapping("/batch")
    public List<StockQuote> getBatchQuotes(@RequestParam List<String> symbols) {
        return stockService.getBatchQuotes(symbols);
    }
}

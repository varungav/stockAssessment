package com.stockAssigment.stockAssigment;

import org.springframework.web.bind.annotation.*;

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
}

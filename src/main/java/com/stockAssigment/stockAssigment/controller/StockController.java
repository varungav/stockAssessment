package com.stockAssigment.stockAssigment.controller;

import com.stockAssigment.stockAssigment.Entity.StockQuote;
import com.stockAssigment.stockAssigment.dto.StockQuoteDTO;
import com.stockAssigment.stockAssigment.dto.StockQuoteMapper;
import com.stockAssigment.stockAssigment.service.StockService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;
    private final StockQuoteMapper stockQuoteMapper;

    public StockController(StockService stockService, StockQuoteMapper stockQuoteMapper) {
        this.stockService = stockService;
        this.stockQuoteMapper = stockQuoteMapper;
    }

    @GetMapping("/daily-open-close/{stocksTicker}/{date}")
    public StockQuoteDTO getDailyOpenClose(
            @PathVariable String stocksTicker,
            @Parameter(
                    description = "Date in the format of YYYY-MM-DD",
                    schema = @Schema(type = "string",format = "date", example = "2024-10-16")
            )
            @PathVariable String date,
            @RequestParam(defaultValue = "true") boolean adjusted) {

        StockQuote stockQuote = stockService.getDailyOpenClose(stocksTicker, date);
        return stockQuoteMapper.toDTO(stockQuote);
    }

    @GetMapping("/batch")
    public List<StockQuoteDTO> getBatchQuotes(@RequestParam List<String> symbols) {
        List<StockQuote> stockQuotes = stockService.getBatchQuotes(symbols);
        return stockQuotes.stream().map(stockQuoteMapper::toDTO)
                .collect(Collectors.toList());
    }
}

package com.stockAssigment.stockAssigment;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class StockService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String apiKey = "sfZSGhRYztcMWiQjgGS53hLKGqgpToEB"; // Your API key

    public StockService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public StockQuote getDailyOpenClose(String stockTicker, String date) {
        String url = String.format("https://api.polygon.io/v1/open-close/%s/%s?adjusted=true&apiKey=%s", stockTicker, date, "sfZSGhRYztcMWiQjgGS53hLKGqgpToEB");

        // Fetch the response from the API
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        // Convert the response to StockQuote
        StockQuote stockQuote = objectMapper.convertValue(response, StockQuote.class);

        // Set additional fields
        if (stockQuote.getOpen() != null && stockQuote.getClose() != null) {
            stockQuote.setPrice(stockQuote.getClose()); // Use close price as latest price
            stockQuote.setChange(stockQuote.getClose().subtract(stockQuote.getOpen())); // Change
            stockQuote.setPercentageChange(stockQuote.getChange()
                    .divide(stockQuote.getOpen(), BigDecimal.ROUND_HALF_UP)
                    .multiply(BigDecimal.valueOf(100))); // Percentage Change
        }

        return stockQuote;
    }

}

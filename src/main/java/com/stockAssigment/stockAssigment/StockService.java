package com.stockAssigment.stockAssigment;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class StockService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public StockService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public StockQuote getDailyOpenClose(String stockTicker, String date) {
        String apiKey = "sfZSGhRYztcMWiQjgGS53hLKGqgpToEB"; // Replace with your actual API key
        String url = String.format("https://api.polygon.io/v1/open-close/%s/%s?adjusted=true&apiKey=%s", stockTicker, date, apiKey);

        // Fetch the response from the API
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        // Create a new StockQuote and populate its fields
        StockQuote stockQuote = new StockQuote();
        stockQuote.setSymbol((String) response.get("symbol"));
        stockQuote.setPrice(BigDecimal.valueOf((Double) response.get("close"))); // Current price
        stockQuote.setChange(BigDecimal.valueOf((Double) response.get("close")) // Assuming change is calculated from close
                .subtract(BigDecimal.valueOf((Double) response.get("open")))); // Change from open to close
        stockQuote.setPercentageChange(stockQuote.getChange()
                .divide(BigDecimal.valueOf((Double) response.get("open")), 4, BigDecimal.ROUND_HALF_UP)
                .multiply(BigDecimal.valueOf(100))); // Calculate percentage change
        stockQuote.setTimestamp(LocalDateTime.now()); // Set current time as the timestamp

        return stockQuote;
    }

}

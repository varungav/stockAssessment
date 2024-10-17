package com.stockAssigment.stockAssigment;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StockService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final StockQuoteRepository stockQuoteRepository; // Add repository

    public StockService(RestTemplate restTemplate, ObjectMapper objectMapper, StockQuoteRepository stockQuoteRepository) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.stockQuoteRepository = stockQuoteRepository; // Initialize repository
    }

    // Method to get daily open-close

    // Method to get batch quotes

    public List<StockQuote> getBatchQuotes(List<String> symbols) {
        List<StockQuote> stockQuotes = new ArrayList<>();
        for (String symbol : symbols) {
            // Assuming we want the latest data for today's date
            String todayDate = "2024-10-15"; // Consider using a dynamic way to get today's date
            StockQuote stockQuote = getDailyOpenClose(symbol, todayDate);
            stockQuotes.add(stockQuote);
        }
        return stockQuotes;
    }
    public StockQuote getDailyOpenClose(String stockTicker, String date) {
        String url = String.format("https://api.polygon.io/v1/open-close/%s/%s?adjusted=true&apiKey=%s", stockTicker, date, "sfZSGhRYztcMWiQjgGS53hLKGqgpToEB");

        // Format the date string to a Date object
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date formattedDate;

        try {
            formattedDate = sdf.parse(date); // Parse the input date string to Date
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format. Expected format: yyyy-MM-dd");
        }

        // Check if the stock quote already exists in the database for the given stockTicker and date
        StockQuote existingQuote = stockQuoteRepository.findBySymbolAndTimestamp(stockTicker, formattedDate);
        if (existingQuote != null) {
            return existingQuote; // Return the existing quote if found
        }

        try {
            // Fetch data from API
            StockQuote stockQuote = restTemplate.getForObject(url, StockQuote.class);

            if (stockQuote == null) {
                throw new RuntimeException("Unexpected response from API.");
            }

            // Validate API response status
            if ("ERROR".equalsIgnoreCase(stockQuote.getStatus())) {
                throw new RuntimeException("Error from API: " + stockQuote.getError());
            }

            // Set additional fields
            if (stockQuote.getOpen() != null && stockQuote.getClose() != null) {
                stockQuote.setPrice(stockQuote.getClose()); // Use close price as latest price
                stockQuote.setPriceChange(stockQuote.getClose().subtract(stockQuote.getOpen())); // Change
                stockQuote.setPercentageChange(stockQuote.getPriceChange()
                        .divide(stockQuote.getOpen(), BigDecimal.ROUND_HALF_UP)
                        .multiply(BigDecimal.valueOf(100))); // Percentage Change
            }

            // Set the timestamp
            stockQuote.setTimestamp(formattedDate);
            stockQuoteRepository.save(stockQuote); // Save to database

            return stockQuote; // Return the newly fetched quote

        } catch (HttpClientErrorException ex) {
            throw new RuntimeException("HTTP Error: " + ex.getStatusCode() + " - " + ex.getResponseBodyAsString());
        } catch (Exception ex) {
            throw new RuntimeException("An error occurred while fetching the stock data: " + ex.getMessage());
        }
    }
}

package com.stockAssigment.stockAssigment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockAssigment.stockAssigment.Entity.StockQuote;
import com.stockAssigment.stockAssigment.repo.StockQuoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


    public List<StockQuote> getBatchQuotes(List<String> symbols) {
        List<StockQuote> stockQuotes = new ArrayList<>();
        for (String symbol : symbols) {
            LocalDate currentDate = LocalDate.now().minusDays(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String todayDate = currentDate.format(formatter); // making it in the above asked format
            StockQuote stockQuote = getDailyOpenClose(symbol, todayDate);
            stockQuotes.add(stockQuote);
        }
        return stockQuotes;
    }
    public StockQuote getDailyOpenClose(String stockTicker, String date) {
        String url = String.format("https://api.polygon.io/v1/open-close/%s/%s?adjusted=true&apiKey=%s", stockTicker, date, "sfZSGhRYztcMWiQjgGS53hLKGqgpToEB");


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date formattedDate;

        try {
            formattedDate = sdf.parse(date); // parse the input date string format to date
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format. Expected format: yyyy-MM-dd");
        }


        StockQuote existingQuote = stockQuoteRepository.findBySymbolAndTimestamp(stockTicker, formattedDate);
        if (existingQuote != null) {
            return existingQuote;
        }

        try {
            StockQuote stockQuote = restTemplate.getForObject(url, StockQuote.class);

            if (stockQuote == null) {
                throw new RuntimeException("Unexpected response from API.");
            }

            // Validate API response status
            if ("ERROR".equalsIgnoreCase(stockQuote.getStatus())) {
                throw new RuntimeException("Error from API: " + stockQuote.getError());
            }


            if (stockQuote.getOpen() != null && stockQuote.getClose() != null) {
                stockQuote.setPrice(stockQuote.getClose()); // Use close price as latest price
                stockQuote.setPriceChange(stockQuote.getClose().subtract(stockQuote.getOpen())); // Change
                stockQuote.setPercentageChange(stockQuote.getPriceChange()
                        .divide(stockQuote.getOpen(), BigDecimal.ROUND_HALF_UP)
                        .multiply(BigDecimal.valueOf(100)));
            }


            stockQuote.setTimestamp(formattedDate);
            stockQuoteRepository.save(stockQuote);

            return stockQuote;

        } catch (HttpClientErrorException ex) {
            throw new RuntimeException("HTTP Error: " + ex.getStatusCode() + " - " + ex.getResponseBodyAsString());
        } catch (Exception ex) {
            throw new RuntimeException("An error occurred while fetching the stock data: " + ex.getMessage());
        }
    }
}

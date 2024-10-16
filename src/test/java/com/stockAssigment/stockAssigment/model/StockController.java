package com.stockAssigment.stockAssigment.model;

import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@RestController
public class StockController {

    // API key for Alpha Vantage (replace 'demo' with your actual key)
    private static final String API_KEY = "demo";

    @GetMapping("/stock")
    public String getStockData() throws JSONException {
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=" + API_KEY;

        // Call the Alpha Vantage API using RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        // Parse the response using org.json
        JSONObject jsonObject = new JSONObject(result);

        // Extracting Meta Data
        JSONObject metaData = jsonObject.getJSONObject("Meta Data");
        String stockName = metaData.getString("2. Symbol");

        // Extracting Time Series data (first entry)
        JSONObject timeSeries = jsonObject.getJSONObject("Time Series (5min)");
        String firstTimestamp = (String) timeSeries.keys().next(); // Get the first timestamp
        JSONObject stockData = timeSeries.getJSONObject(firstTimestamp);

        String lowestPrice = stockData.getString("3. low");
        String highestPrice = stockData.getString("2. high");

        // Build a response message
        return "Stock: " + stockName + "\n" +
                "Timestamp: " + firstTimestamp + "\n" +
                "Lowest Price: " + lowestPrice + "\n" +
                "Highest Price: " + highestPrice;
    }
}

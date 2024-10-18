package com.stockAssigment.stockAssigment.dto;

import java.math.BigDecimal;
import java.util.Date;

public class StockQuoteDTO {
    private Long id;
    private String symbol;
    private BigDecimal highest;
    private BigDecimal lowest;
    private BigDecimal price;
    private BigDecimal priceChange;
    private BigDecimal percentageChange;
    private Date timeStamp;

    public StockQuoteDTO(Long id, String symbol, BigDecimal highest, BigDecimal lowest, BigDecimal price, BigDecimal priceChange, BigDecimal percentageChange, Date timeStamp) {
        this.id = id;
        this.symbol = symbol;
        this.highest = highest;
        this.lowest = lowest;
        this.price = price;
        this.priceChange = priceChange;
        this.percentageChange = percentageChange;
        this.timeStamp = timeStamp;
    }

    //getters& setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getHighest() {
        return highest;
    }

    public void setHighest(BigDecimal highest) {
        this.highest = highest;
    }

    public BigDecimal getLowest() {
        return lowest;
    }

    public void setLowest(BigDecimal lowest) {
        this.lowest = lowest;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(BigDecimal priceChange) {
        this.priceChange = priceChange;
    }

    public BigDecimal getPercentageChange() {
        return percentageChange;
    }

    public void setPercentageChange(BigDecimal percentageChange) {
        this.percentageChange = percentageChange;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}

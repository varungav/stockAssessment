package com.stockAssigment.stockAssigment.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class StockQuote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;
    private BigDecimal open;
    private BigDecimal close;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal afterHours;
    private BigDecimal preMarket;
    private Long volume;
    private String fromSource;
    private String status;
    private String error;
    private BigDecimal price;
    private BigDecimal priceChange;

    private BigDecimal percentageChange;
    private Date timestamp;

    //constructors

    public StockQuote(String symbol, BigDecimal open, BigDecimal close) {
        this.symbol = symbol;
        this.open = open;
        this.close = close;
    }

    public StockQuote() {
    }

    // Getters and setters
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

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getAfterHours() {
        return afterHours;
    }

    public void setAfterHours(BigDecimal afterHours) {
        this.afterHours = afterHours;
    }

    public BigDecimal getPreMarket() {
        return preMarket;
    }

    public void setPreMarket(BigDecimal preMarket) {
        this.preMarket = preMarket;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public String getFromSource() { // Getter for 'fromSource'
        return fromSource;
    }

    public void setFromSource(String fromSource) { // Setter for 'fromSource'
        this.fromSource = fromSource;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}

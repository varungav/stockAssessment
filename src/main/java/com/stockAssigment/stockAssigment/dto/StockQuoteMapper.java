package com.stockAssigment.stockAssigment.dto;

import com.stockAssigment.stockAssigment.Entity.StockQuote;
import com.stockAssigment.stockAssigment.dto.StockQuoteDTO;
import org.springframework.stereotype.Component;

@Component
public class StockQuoteMapper {
    public StockQuoteDTO toDTO(StockQuote stockQuote) {
        return new StockQuoteDTO(
                stockQuote.getId(),
                stockQuote.getSymbol(),
                stockQuote.getHigh(),
                stockQuote.getLow(),
                stockQuote.getPrice(),
                stockQuote.getPriceChange(),
                stockQuote.getPercentageChange(),
                stockQuote.getTimestamp()
        );
    }

    public StockQuote toEntity(StockQuoteDTO stockQuoteDTO) {
        StockQuote stockQuote = new StockQuote();
        stockQuote.setId(stockQuoteDTO.getId());
        stockQuote.setSymbol(stockQuoteDTO.getSymbol());
        stockQuote.setPrice(stockQuoteDTO.getPrice());
        stockQuote.setPriceChange(stockQuoteDTO.getPriceChange());
        stockQuote.setPercentageChange(stockQuoteDTO.getPercentageChange());
        stockQuote.setTimestamp(stockQuoteDTO.getTimeStamp());
        stockQuote.setHigh(stockQuoteDTO.getHighest());
        stockQuote.setLow(stockQuoteDTO.getLowest());
        return stockQuote;
    }
}

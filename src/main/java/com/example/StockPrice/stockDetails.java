package com.example.StockPrice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class stockDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public  String stockName;
    public String stockExchange;
    public BigDecimal currentPrice;
    public BigDecimal closingPrice;
    public LocalDateTime date;

    public stockDetails(){}

    public stockDetails(String stockName, String stockExchange, BigDecimal currentPrice, BigDecimal closingPrice ,LocalDateTime date) {
        this.stockName = stockName;
        this.stockExchange = stockExchange;
        this.currentPrice = currentPrice;
        this.closingPrice = closingPrice;
        this.date = date;
    }

    public LocalDateTime getStockDetailsDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getStockDetailsId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStockDetailsStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockDetailsStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(String stockExchange) {
        this.stockExchange = stockExchange;
    }

    public BigDecimal getStockDetailsCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public BigDecimal getStockDetailsClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(BigDecimal closingPrice) {
        this.closingPrice = closingPrice;
    }
}

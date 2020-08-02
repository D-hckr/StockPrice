package com.example.StockPrice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class fetchStock{

    @Autowired
    public stockRepo stockRepo;

    public String stockName;
    public String stockExchange;
    public BigDecimal currentPrice;
    public BigDecimal closingPrice;


    public String getStockName(String companyName) throws IOException{
        stockName = YahooFinance.get(companyName).getName();
        return stockName;
    }



    public String getStockExchange(String companyName) throws IOException {
        stockExchange = YahooFinance.get(companyName).getStockExchange();
        return stockExchange;
    }


    public BigDecimal getCurrentPrice(String companyName) throws IOException{
        currentPrice = YahooFinance.get(companyName).getQuote().getPrice();
        return currentPrice;
    }

    public BigDecimal getClosingPrice(String companyName) throws IOException{
        closingPrice = YahooFinance.get(companyName).getQuote().getPreviousClose();
        return closingPrice;
    }

    public Map<String, fetchStock> getStockDetails(List<String> lst) throws Exception{
        Map<String,fetchStock> mp = new HashMap<>();
        for (String companyName : lst) {
            fetchStock fetchStockObj = new fetchStock();
            try {
                fetchStockObj.getStockName(companyName);
                fetchStockObj.getCurrentPrice(companyName);
                fetchStockObj.getClosingPrice(companyName);
                fetchStockObj.getStockExchange(companyName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stockRepo.save(new stockDetails(fetchStockObj.getStockName(companyName),fetchStockObj.getStockExchange(companyName),fetchStockObj.getCurrentPrice(companyName),fetchStockObj.getClosingPrice(companyName), LocalDateTime.now()));
            mp.put(companyName, fetchStockObj);
        }
        return mp;
    }

}

package com.example.StockPrice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class stockService {

    @Autowired
    private stockRepo stockRepo;

    public List<stockDetails> getTable(){
        List<stockDetails> stockDetailsList = new ArrayList<>();
        Iterable<stockDetails> itr = stockRepo.findAll();
        itr.forEach(e -> stockDetailsList.add(e));
        return stockDetailsList;
    }

    public Long count(){
        return stockRepo.count();
    }
}

package com.example.StockPrice;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface stockRepo extends CrudRepository<stockDetails,Integer> {
}

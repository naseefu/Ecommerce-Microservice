package com.lustre.stock_service.repo;

import com.lustre.stock_service.entity.Stocks;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepo extends MongoRepository<Stocks,String> {
    Stocks findByProductId(String id);
}

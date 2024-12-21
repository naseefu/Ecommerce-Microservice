package com.lustre.product_service.repo;

import com.lustre.product_service.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product,String>, PagingAndSortingRepository<Product, String> {
}

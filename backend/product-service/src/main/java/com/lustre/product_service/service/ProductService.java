package com.lustre.product_service.service;

import com.lustre.product_service.DTO.ProductDTO;
import com.lustre.product_service.DTO.Response;
import com.lustre.product_service.DTO.Stocks;
import com.lustre.product_service.config.StockClient;
import com.lustre.product_service.entity.Category;
import com.lustre.product_service.entity.Product;
import com.lustre.product_service.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockClient stockClient;

    public Response addProduct(ProductDTO productDTO) {

        Response response = new Response();

        try{

            Product product = new Product();

            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setDescription(productDTO.getDescription());
            product.setCategory(Category.valueOf(productDTO.getCategory()).toString());
            product.setCurrency(productDTO.getCurrency());
            product.setDiscountPrice(productDTO.getDiscountPrice());
            product.setImage(productDTO.getImage());
            product.setDate(LocalDateTime.now());
            product.setBrand(productDTO.getBrand());

            Product p = productRepository.save(product);

            Stocks stocks = new Stocks();
            stocks.setProductId(p.getId());
            stocks.setDate(p.getDate());
            stocks.setXsmall(productDTO.getXsmall());
            stocks.setSmall(productDTO.getSmall());
            stocks.setMedium(productDTO.getMedium());
            stocks.setLarge(productDTO.getLarge());
            stocks.setXlarge(productDTO.getXlarge());

            String s = stockClient.saveStock(stocks);

            if(s.equals("success")){

                response.setCode(200);
                response.setMessage("Product added successfully");

            }
            else{

                productRepository.delete(p);
                response.setCode(500);
                response.setMessage("Failed to add product");

            }

            return response;


        }
        catch(Exception e){

            response.setCode(500);
            response.setMessage(e.getMessage());
            return response;

        }

    }

    public Response getEachProduct(String id) {

        Response response = new Response();

        try {

            Product p = new Product();

            if(id!=null) {

                Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));

                p.setId(product.getId());
                p.setName(product.getName());
                p.setPrice(product.getPrice());
                p.setDescription(product.getDescription());
                p.setCategory(product.getCategory());
                p.setCurrency(product.getCurrency());
                p.setDiscountPrice(product.getDiscountPrice());
                p.setImage(product.getImage());
                p.setDate(product.getDate());
                p.setBrand(product.getBrand());

                response.setProduct(p);

                Stocks s = stockClient.getStock(id);

                response.setStocks(s);

                response.setCode(200);
                response.setMessage("Success");
            }
            else{
                response.setCode(500);
                response.setMessage("Failed to find product");
            }
            return response;


        }
        catch(Exception e){
            response.setCode(500);
            response.setMessage(e.getMessage());
            return response;
        }


    }


    public Response getAllProducts(Pageable pageable) {

        Response response = new Response();

        try{

            Page<Product> p = productRepository.findAll(pageable);

            p.stream().forEach(f->{
                f.setCurrency(Currency.getInstance(f.getCurrency()).getSymbol());
            });

            response.setProducts(p);
            response.setCode(200);
            response.setMessage("Fetched Product list successfully");
            return response;

        }
        catch(Exception e){
            response.setCode(500);
            response.setMessage(e.getMessage());
            return response;
        }

    }
}

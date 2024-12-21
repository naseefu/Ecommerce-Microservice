package com.lustre.product_service.controller;

import com.lustre.product_service.DTO.ProductDTO;
import com.lustre.product_service.DTO.Response;
import com.lustre.product_service.entity.Product;
import com.lustre.product_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/hellos")
    @ResponseStatus(HttpStatus.OK)
    public String response(){
        return "success";
    }

    @PostMapping("/save")
    public ResponseEntity<Response> addProduct(@RequestBody @Valid ProductDTO productDTO) {

        Response response = productService.addProduct(productDTO);
        return ResponseEntity.status(response.getCode()).body(response);

    }

    @GetMapping("/each/{id}")
    public ResponseEntity<Response> getEachProduct(@PathVariable String id){
        Response response = productService.getEachProduct(id);
        return ResponseEntity.status(response.getCode()).body(response);

    }

    @GetMapping("/each-order/{id}")
    public Product getEachProductForOrder(@PathVariable String id){
        Response response = productService.getEachProduct(id);
        return response.getProduct();

    }

    @GetMapping("/all/{num}/{size}")
    public ResponseEntity<Response> getAllProduct(@PathVariable int num,@PathVariable int size){

        Pageable pageable = PageRequest.of(num, size);

        Response response = productService.getAllProducts(pageable);
        return ResponseEntity.status(response.getCode()).body(response);

    }

}

package com.lustre.product_service.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;

@Document(collection = "product")
public class Product {

    @Id
    private String id;

    @NotNull(message = "Product name cannot be empty")
    private String name;

    @NotNull(message = "Product description cannot be null")
    @Lob
    private String description;

    @NotNull(message = "Image cannot be null")
    @Lob
    private String image;

    @NotNull(message = "Category cannot be null")
    private String category;

    @NotNull(message = "Brand name cannot be null")
    private String brand;

    @Min(value = 0,message = "Price must be greater than zero")
    private Double price;

    @Min(value = 0,message = "Price must be greater than zero")
    private Double discountPrice;

    @NotNull(message = "Specify the currency")
    private String currency;

    private LocalDateTime date;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

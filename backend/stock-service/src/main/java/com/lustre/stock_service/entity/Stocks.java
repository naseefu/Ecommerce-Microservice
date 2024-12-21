package com.lustre.stock_service.entity;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "stocks")
public class Stocks {

    @Id
    private String id;

    @NotNull(message = "product Id cannot be null")
    private String productId;

    private Long xsmall;
    private Long small;
    private Long medium;
    private Long large;
    private Long xlarge;

    private LocalDateTime date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getXsmall() {
        return xsmall;
    }

    public void setXsmall(Long xsmall) {
        this.xsmall = xsmall;
    }

    public Long getSmall() {
        return small;
    }

    public void setSmall(Long small) {
        this.small = small;
    }

    public Long getMedium() {
        return medium;
    }

    public void setMedium(Long medium) {
        this.medium = medium;
    }

    public Long getLarge() {
        return large;
    }

    public void setLarge(Long large) {
        this.large = large;
    }

    public Long getXlarge() {
        return xlarge;
    }

    public void setXlarge(Long xlarge) {
        this.xlarge = xlarge;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

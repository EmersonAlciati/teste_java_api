package com.ntconsult.testentconsult.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Produto {

    private int id;
    private String title;
    private String description;
    private BigDecimal price;
    private BigDecimal discountPercentage;
    private BigDecimal rating;
    private int stock;
    private List<String> tags;
    private String brand;
    private String category;
    private String thumbnail;
    private List<String> images;
    //campos adcionais
    private String sku;
    private BigDecimal weigth;
    private String warrantyInformation;
    private String shippingInformation;
    private String availabilityStatus;
    private List<Reviews> reviews;
    private String returnPolicy;
    private String minimumOrderQuantity;
    private Meta meta;
    private Dimensions dimensions;

    
}

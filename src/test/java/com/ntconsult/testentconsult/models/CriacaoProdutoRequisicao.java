package com.ntconsult.testentconsult.models;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CriacaoProdutoRequisicao {
    private String title;
    private String description;
    private BigDecimal price;
    private BigDecimal discountPercentage;
    private BigDecimal rating;
    private int stock;
    private String brand;
    private String category;
    private String thumbnail;
    
}

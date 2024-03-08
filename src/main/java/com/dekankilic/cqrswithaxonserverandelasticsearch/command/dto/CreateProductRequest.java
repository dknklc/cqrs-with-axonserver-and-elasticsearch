package com.dekankilic.cqrswithaxonserverandelasticsearch.command.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
}

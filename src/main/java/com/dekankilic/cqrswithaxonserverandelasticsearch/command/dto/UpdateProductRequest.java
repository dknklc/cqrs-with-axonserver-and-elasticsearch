package com.dekankilic.cqrswithaxonserverandelasticsearch.command.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductRequest {
    private String id;
    private BigDecimal price;
    private int stock;
}

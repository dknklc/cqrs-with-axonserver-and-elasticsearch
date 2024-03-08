package com.dekankilic.cqrswithaxonserverandelasticsearch.common.event;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductCreatedEvent extends BaseEvent<String> {
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;

    public ProductCreatedEvent(String id, String name, String description, BigDecimal price, int stock) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}

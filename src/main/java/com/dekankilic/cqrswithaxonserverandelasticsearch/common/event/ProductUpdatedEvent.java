package com.dekankilic.cqrswithaxonserverandelasticsearch.common.event;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductUpdatedEvent extends BaseEvent<String>{
    private BigDecimal price;
    private int stock;

    public ProductUpdatedEvent(String id, BigDecimal price, int stock) {
        super(id);
        this.price = price;
        this.stock = stock;
    }
}

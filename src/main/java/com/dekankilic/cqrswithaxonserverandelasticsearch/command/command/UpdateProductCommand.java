package com.dekankilic.cqrswithaxonserverandelasticsearch.command.command;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class UpdateProductCommand extends BaseCommand<String> {
    private BigDecimal price;
    private int stock;

    public UpdateProductCommand(String id, BigDecimal price, int stock) {
        super(id);
        this.price = price;
        this.stock = stock;
    }
}

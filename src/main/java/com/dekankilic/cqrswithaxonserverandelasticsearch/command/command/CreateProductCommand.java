package com.dekankilic.cqrswithaxonserverandelasticsearch.command.command;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateProductCommand extends BaseCommand<String> {
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;

    public CreateProductCommand(String id, String name, String description, BigDecimal price, int stock) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}

package com.dekankilic.cqrswithaxonserverandelasticsearch.command.aggregate;

import java.math.BigDecimal;

public class ProductAggregate {  // We hold the state of a product at a certain point in time. We'll be writing in here the code to handle different commands and events so that an instance of this aggregate gets saved in the Event Store as an event.

    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
}

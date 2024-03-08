package com.dekankilic.cqrswithaxonserverandelasticsearch.query.controller;

import com.dekankilic.cqrswithaxonserverandelasticsearch.query.model.Product;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ElasticProductController {
    private final QueryGateway queryGateway;

    public ElasticProductController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/get-product")
    public ResponseEntity<Product> getProduct(@RequestParam String id){
        return null;
    }
}

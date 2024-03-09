package com.dekankilic.cqrswithaxonserverandelasticsearch.query.controller;

import com.dekankilic.cqrswithaxonserverandelasticsearch.query.model.Product;
import com.dekankilic.cqrswithaxonserverandelasticsearch.query.query.*;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/products")
public class ElasticProductController {
    private final QueryGateway queryGateway;

    public ElasticProductController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }


    // TODO get the product with given id
    @GetMapping("/get-product")
    public ResponseEntity<Product> getProduct(@RequestParam String id){
        Product product = queryGateway.query(new FindProductById(id), Product.class).join();
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(product);
    }

    // TODO getAllDataFromIndex
    @GetMapping("/get-all-products/{indexName}")
    public ResponseEntity<List<Product>> getAllProductsFromIndex(@PathVariable String indexName){
        List<Product> products = queryGateway.query(new FindAllProducts(indexName), ResponseTypes.multipleInstancesOf(Product.class)).join();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(products);
    }

    // TODO searchProductsByFieldAndValue
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductsByFieldAndValue(@RequestParam String field, @RequestParam String value){
        List<Product> products = queryGateway.query(new FindProductByFieldAndValue(field, value), ResponseTypes.multipleInstancesOf(Product.class)).join();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(products);
    }

    // TODO boolQuery
    @GetMapping("/boolQuery")
    public ResponseEntity<List<Product>> boolQuery(@RequestParam String field1, @RequestParam String value1, @RequestParam String field2, @RequestParam String value2){
        List<Product> products = queryGateway.query(new FindProductByBoolQuery(field1, value1, field2, value2), ResponseTypes.multipleInstancesOf(Product.class)).join();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(products);
    }

    // TODO autoSuggestProductsByName
    @GetMapping("/autoSuggest/{name}")
    public ResponseEntity<List<String>> autoSuggestProductsByName(@PathVariable String name){
        List<String> response = queryGateway.query(new FindSuggestedProductsByName(name), ResponseTypes.multipleInstancesOf(String.class)).join();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}

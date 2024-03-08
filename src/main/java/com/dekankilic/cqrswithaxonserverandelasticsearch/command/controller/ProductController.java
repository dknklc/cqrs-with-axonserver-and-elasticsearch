package com.dekankilic.cqrswithaxonserverandelasticsearch.command.controller;

import com.dekankilic.cqrswithaxonserverandelasticsearch.command.dto.CreateProductRequest;
import com.dekankilic.cqrswithaxonserverandelasticsearch.command.dto.UpdateProductRequest;
import com.dekankilic.cqrswithaxonserverandelasticsearch.command.service.ProductCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductCommandService productCommandService;

    public ProductController(ProductCommandService productCommandService) {
        this.productCommandService = productCommandService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody CreateProductRequest createProductRequest){
        return null;
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody UpdateProductRequest updateProductRequest){
        return null;
    }

}

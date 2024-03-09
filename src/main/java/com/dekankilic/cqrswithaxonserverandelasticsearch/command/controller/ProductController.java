package com.dekankilic.cqrswithaxonserverandelasticsearch.command.controller;

import com.dekankilic.cqrswithaxonserverandelasticsearch.command.dto.CreateProductRequest;
import com.dekankilic.cqrswithaxonserverandelasticsearch.command.dto.UpdateProductRequest;
import com.dekankilic.cqrswithaxonserverandelasticsearch.command.service.ProductCommandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductCommandService productCommandService;

    public ProductController(ProductCommandService productCommandService) {
        this.productCommandService = productCommandService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody CreateProductRequest createProductRequest){

        try{
            CompletableFuture<String> response = productCommandService.createProduct(createProductRequest);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response.get());
        }catch (Exception e){
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody UpdateProductRequest updateProductRequest){
        try{
            CompletableFuture<String> response = productCommandService.updateProduct(updateProductRequest);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Product Updated");
        } catch (Exception e){
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

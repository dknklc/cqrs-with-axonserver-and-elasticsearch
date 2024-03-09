package com.dekankilic.cqrswithaxonserverandelasticsearch.command.service;

import com.dekankilic.cqrswithaxonserverandelasticsearch.command.command.CreateProductCommand;
import com.dekankilic.cqrswithaxonserverandelasticsearch.command.command.UpdateProductCommand;
import com.dekankilic.cqrswithaxonserverandelasticsearch.command.dto.CreateProductRequest;
import com.dekankilic.cqrswithaxonserverandelasticsearch.command.dto.UpdateProductRequest;
import org.springframework.stereotype.Service;
import org.axonframework.commandhandling.gateway.CommandGateway;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductCommandService {
    private final CommandGateway commandGateway;

    public ProductCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    // it returns CompletableFuture, which means it will not have to wait for the command to fully execute
    // it just returns to us the productId that we generated. Why does it return the accountId? That is how Axon implemented it internally.
    public CompletableFuture<String> createProduct(CreateProductRequest createProductRequest){
        return commandGateway.send(new CreateProductCommand(
                UUID.randomUUID().toString(),
                createProductRequest.getName(),
                createProductRequest.getDescription(),
                createProductRequest.getPrice(),
                createProductRequest.getStock()
        ));
    }

    /******************************************************************************************************************/

    public CompletableFuture<String> updateProduct(UpdateProductRequest updateProductRequest){
        return commandGateway.send(new UpdateProductCommand(
                updateProductRequest.getId(),
                updateProductRequest.getPrice(),
                updateProductRequest.getStock()
        ));
    }
}

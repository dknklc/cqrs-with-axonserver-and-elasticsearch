package com.dekankilic.cqrswithaxonserverandelasticsearch.command.service;

import org.springframework.stereotype.Service;
import org.axonframework.commandhandling.gateway.CommandGateway;

@Service
public class ProductCommandService {
    private final CommandGateway commandGateway;

    public ProductCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }
}

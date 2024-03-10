package com.dekankilic.cqrswithaxonserverandelasticsearch.query.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.dekankilic.cqrswithaxonserverandelasticsearch.common.event.ProductCreatedEvent;
import com.dekankilic.cqrswithaxonserverandelasticsearch.common.event.ProductUpdatedEvent;
import com.dekankilic.cqrswithaxonserverandelasticsearch.query.model.Product;
import com.dekankilic.cqrswithaxonserverandelasticsearch.query.query.*;
import com.dekankilic.cqrswithaxonserverandelasticsearch.query.repository.ProductRepository;
import com.dekankilic.cqrswithaxonserverandelasticsearch.query.util.ESUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ElasticsearchClient elasticsearchClient; // tüm sorgularımızı bunun üzerinden yapacağız.

    @EventHandler // to let Axon know that this method should be executed to handle the product created event
    public void on(ProductCreatedEvent productCreatedEvent){
        log.info("Handling ProductCreatedEvent...");

        Product product = new Product();
        product.setId(productCreatedEvent.getId());
        product.setName(productCreatedEvent.getName());
        product.setDescription(productCreatedEvent.getDescription());
        product.setStock(productCreatedEvent.getStock());
        product.setPrice(productCreatedEvent.getPrice());
        productRepository.save(product);
    }

    /******************************************************************************************************************/

    @EventHandler
    public void on(ProductUpdatedEvent productUpdatedEvent){
        log.info("Handling ProductUpdatedEvent...");

        Product product = productRepository.findById(productUpdatedEvent.getId()).orElse(null);
        if(product != null){
            product.setPrice(productUpdatedEvent.getPrice());
            product.setStock(product.getStock());
            productRepository.save(product);
        }
    }

    // What is left for us to do is to handle requests which we will receive through the RestApi that we will open for getting the account details.
    // Let's write the method that returns an Account and takes in a FindAccountByIdQuery.
    @QueryHandler // to let Axon know that this is the handler for FindAccountByIdQuery.
    public Product handle(FindProductById query){
        log.info("Handling FindProductByIdQuery...");
        Product product = productRepository.findById(query.getId()).orElse(null);

        return product;
    }

    @QueryHandler
    public List<Product> handle(FindProductByFieldAndValue findProductByFieldAndValue){
        Supplier<Query> query = ESUtil.buildQueryForFieldAndValue(findProductByFieldAndValue.getField(), findProductByFieldAndValue.getValue());
        log.info("Elasticsearch query {}", query.toString());
        SearchResponse<Product> response = null;
        try {
            response = elasticsearchClient.search(q -> q.index("products").query(query.get()), Product.class);

        } catch (IOException e){
            throw new RuntimeException(e);
        }

        log.info("Elasticsearch response {}", response);
        return response.hits()
                .hits()
                .stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }

    @QueryHandler
    public List<Product> handle(FindAllProducts findAllProducts){
        var query = ESUtil.createMatchAllQuery();
        log.info("Elasticsearch query {}", query.toString());
        SearchResponse<Product> response = null;
        try {
            response = elasticsearchClient.search(q -> q.index(findAllProducts.getIndexName()).query(query), Product.class);
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        log.info("Elasticsearch response {}", response);
        return response.hits()
                .hits()
                .stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }

    @QueryHandler
    public List<Product> handle(FindProductByBoolQuery findProductByBoolQuery){
        var query = ESUtil.createBoolQuery(findProductByBoolQuery.getField1(), findProductByBoolQuery.getValue1(), findProductByBoolQuery.getField2(), findProductByBoolQuery.getValue2());
        log.info("Elasticsearch query {}", query.toString());
        SearchResponse<Product> response = null;
        try{
            response = elasticsearchClient.search(q -> q.index("products").query(query.get()), Product.class);
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        log.info("Elasticsearch response {}", response);
        return response.hits()
                .hits()
                .stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }

    
    /*@QueryHandler
    public Set<String> handle(FindSuggestedProductsByName findSuggestedProductsByName){
        var query = ESUtil.buildAutoSuggestQuery(findSuggestedProductsByName.getName());
        log.info("Elasticsearch query {}", query.toString());
        try {
            return elasticsearchClient.search(q -> q.index("products").query(query), Product.class)
                    .hits()
                    .hits()
                    .stream()
                    .map(Hit::source)
                    .map(Product::getName)
                    .collect(Collectors.toSet());

        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }*/

    @QueryHandler
    public Set<String> handle(FindSuggestedProductsByName findSuggestedProductsByName){
        List<Product> products = productRepository.customAutocompleteSearch(findSuggestedProductsByName.getName());
        log.info("Elasticsearch response: {}", products.toString());
        return products
                .stream()
                .map(Product::getName)
                .collect(Collectors.toSet());

    }


}

package com.dekankilic.cqrswithaxonserverandelasticsearch.query.repository;

import com.dekankilic.cqrswithaxonserverandelasticsearch.query.model.Product;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, String> {

    @Query("{\"bool\": {\"must\": {\"match_phrase_prefix\": {\"name\": \"?0\"}}}}")
    List<Product> customAutocompleteSearch(String input);
}

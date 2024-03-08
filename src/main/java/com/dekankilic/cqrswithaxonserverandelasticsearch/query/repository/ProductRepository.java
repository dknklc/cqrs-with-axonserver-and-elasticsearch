package com.dekankilic.cqrswithaxonserverandelasticsearch.query.repository;

import com.dekankilic.cqrswithaxonserverandelasticsearch.query.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, String> {

}

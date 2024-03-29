package com.dekankilic.cqrswithaxonserverandelasticsearch.query.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.dekankilic.cqrswithaxonserverandelasticsearch.query.repository")
@ComponentScan(basePackages = {"com.dekankilic.cqrswithaxonserverandelasticsearch"})
public class ESConfig extends ElasticsearchConfiguration {
    @Value("${elasticsearch.url}")
    private String url;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(url)
                .build();
    }
}

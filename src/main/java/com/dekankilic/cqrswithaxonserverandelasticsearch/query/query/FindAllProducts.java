package com.dekankilic.cqrswithaxonserverandelasticsearch.query.query;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAllProducts {
    private String indexName;
}

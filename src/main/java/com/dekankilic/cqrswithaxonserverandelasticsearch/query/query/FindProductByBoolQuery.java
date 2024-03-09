package com.dekankilic.cqrswithaxonserverandelasticsearch.query.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class FindProductByBoolQuery {
    private String field1;
    private String value1;
    private String field2;
    private String value2;

}

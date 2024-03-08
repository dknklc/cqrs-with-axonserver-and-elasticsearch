package com.dekankilic.cqrswithaxonserverandelasticsearch.query.query;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class FindProductByFieldAndValue {
    private String field;
    private String value;
}

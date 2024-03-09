package com.dekankilic.cqrswithaxonserverandelasticsearch.query.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class FindProductByFieldAndValue {
    private String field;
    private String value;
}

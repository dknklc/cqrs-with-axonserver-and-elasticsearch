package com.dekankilic.cqrswithaxonserverandelasticsearch.query.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class FindSuggestedProductsByName {
    private String name;
}

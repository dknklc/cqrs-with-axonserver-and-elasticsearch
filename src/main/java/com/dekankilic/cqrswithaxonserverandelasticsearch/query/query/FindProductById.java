package com.dekankilic.cqrswithaxonserverandelasticsearch.query.query;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class FindProductById {
    private String id;
}

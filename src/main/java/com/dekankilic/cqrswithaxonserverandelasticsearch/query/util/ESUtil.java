package com.dekankilic.cqrswithaxonserverandelasticsearch.query.util;

import co.elastic.clients.elasticsearch._types.query_dsl.*;
import lombok.experimental.UtilityClass;

import java.util.function.Supplier;

@UtilityClass
public class ESUtil {


    public static Supplier<Query> buildQueryForFieldAndValue(String field, String value){
        return () -> Query.of(q -> q.match(buildMatchQueryForFieldAndValue(field, value)));
    }

    private static MatchQuery buildMatchQueryForFieldAndValue(String field, String value){
        return new MatchQuery.Builder()
                .field(field)
                .query(value)
                .build();
    }

    public static Query createMatchAllQuery() {
        return Query.of(q -> q.matchAll(new MatchAllQuery.Builder().build()));
    }

    public static Supplier<Query> createBoolQuery(String field1, String value1, String field2, String value2) {
        return () -> Query.of(q -> q.bool(boolQuery(field1, value1, field2, value2)));
    }

    private static BoolQuery boolQuery(String field1, String value1, String field2, String value2){
        return new BoolQuery.Builder()
                .filter(termQuery(field1, value1))
                .must(matchQuery(field2, value2))
                .build();
    }

    private static Query termQuery(String field, String value){
        return Query.of(q -> q.term(new TermQuery.Builder()
                .field(field)
                .value(value)
                .build()));
    }

    private static Query matchQuery(String field, String value){
        return Query.of(q -> q.match(new MatchQuery.Builder()
                .field(field)
                .query(value)
                .build()));
    }

    public static Query buildAutoSuggestQuery(String name) {
        return Query.of(q -> q.match(new MatchQuery.Builder()
                .field("name")
                .query(name)
                .analyzer("custom_index")
                .build()));
    }
}

package com.dekankilic.cqrswithaxonserverandelasticsearch.common.event;

import lombok.Getter;

@Getter
public class BaseEvent<T> {
    private T id;

    public BaseEvent(T id) {
        this.id = id;
    }
}

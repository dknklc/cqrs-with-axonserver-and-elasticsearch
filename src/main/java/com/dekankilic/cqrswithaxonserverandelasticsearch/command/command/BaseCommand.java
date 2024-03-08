package com.dekankilic.cqrswithaxonserverandelasticsearch.command.command;

import lombok.Getter;

@Getter
public class BaseCommand<T> {
    private final T id; // Each command class will have an id for Axon to identify which instance of the aggregate that is applied on.

    public BaseCommand(T id){
        this.id = id;
    }
}

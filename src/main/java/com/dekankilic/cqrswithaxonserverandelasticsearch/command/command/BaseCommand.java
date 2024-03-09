package com.dekankilic.cqrswithaxonserverandelasticsearch.command.command;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
public class BaseCommand<T> {
    @TargetAggregateIdentifier // to let Axon know that the id field is the unique identifier of the AccountAggregate that the command targets
    private final T id; // Each command class will have an id for Axon to identify which instance of the aggregate that is applied on.

    public BaseCommand(T id){
        this.id = id;
    }
}

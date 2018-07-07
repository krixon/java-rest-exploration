package com.krixon.javarest.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import java.util.UUID;

public class OrderResource extends ResourceSupport
{
    private final UUID id;
    private final String description;
    private final MoneyResource cost;
    private final boolean isComplete;

    @JsonCreator
    OrderResource(
        @JsonProperty("id") UUID id,
        @JsonProperty("description") String description,
        @JsonProperty("cost") MoneyResource cost,
        @JsonProperty("isComplete") boolean isComplete)
    {
        this.id = id;
        this.description = description;
        this.cost = cost;
        this.isComplete = isComplete;
    }

    @JsonProperty("id")
    UUID resourceId()
    {
        return id;
    }

    @JsonProperty("description")
    String description()
    {
        return description;
    }

    @JsonProperty("cost")
    MoneyResource cost()
    {
        return cost;
    }

    @JsonProperty("isComplete")
    boolean isComplete()
    {
        return isComplete;
    }
}
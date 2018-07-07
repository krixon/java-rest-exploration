package com.krixon.javarest.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;

public class MoneyResource extends ResourceSupport
{
    private BigDecimal amount;
    private String currency;

    @JsonCreator
    MoneyResource(
        @JsonProperty("amount") BigDecimal amount,
        @JsonProperty("currency") String currency)
    {
        this.amount = amount;
        this.currency = currency;
    }

    @JsonCreator
    MoneyResource(
        @JsonProperty("amount") String amount,
        @JsonProperty("currency") String currency)
    {
        this.amount = new BigDecimal(amount);
        this.currency = currency;
    }

    @JsonProperty
    public BigDecimal amount()
    {
        return amount;
    }

    @JsonProperty
    public String currency()
    {
        return currency;
    }
}

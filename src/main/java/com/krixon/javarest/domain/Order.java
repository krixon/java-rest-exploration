package com.krixon.javarest.domain;

import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.util.UUID;

public class Order implements Identifiable
{
    private UUID id;
    private String description;
    private MonetaryAmount cost;
    private boolean isComplete;

    public Order(UUID id, MonetaryAmount cost)
    {
        this.id = id;
        this.cost = cost;
    }

    @Override
    public UUID getId()
    {
        return id;
    }

    public String getDescription()
    {
        return description;
    }

    public void describe(String description)
    {
        this.description = description;
    }

    public void setCost(Money cost)
    {
        this.cost = cost;
    }

    public MonetaryAmount getCost()
    {
        return cost;
    }

    public void setComplete(boolean isComplete)
    {
        this.isComplete = isComplete;
    }

    public void markComplete()
    {
        setComplete(true);
    }

    public void markIncomplete()
    {
        setComplete(false);
    }

    public boolean isComplete()
    {
        return isComplete;
    }
}

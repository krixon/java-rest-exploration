package com.krixon.javarest.resource;

import com.krixon.javarest.domain.Order;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.math.BigDecimal;

@Component
public class OrderResourceMapper extends ResourceMapper<Order, OrderResource>
{
    @Autowired
    protected EntityLinks entityLinks;
    private static final String UPDATE_REL = "update";
    private static final String DELETE_REL = "remove";

    @Override
    public OrderResource toResource(Order order)
    {
        OrderResource resource = new OrderResource(
            order.getId(),
            order.getDescription(),
            new MoneyResource(
                order.getCost().getNumber().numberValueExact(BigDecimal.class),
                order.getCost().getCurrency().toString()
            ),
            order.isComplete()
        );

        final Link selfLink = entityLinks.linkToSingleResource(order);

        resource.add(selfLink.withSelfRel());
        resource.add(selfLink.withRel(UPDATE_REL));
        resource.add(selfLink.withRel(DELETE_REL));

        return resource;
    }

    @Override
    public Order fromResource(OrderResource resource)
    {
        MonetaryAmount cost = Monetary
            .getDefaultAmountFactory()
            .setNumber(resource.cost().amount())
            .setCurrency(resource.cost().currency())
            .create();

        Order order = new Order(resource.resourceId(), cost);

        order.describe(resource.description());

        if (resource.isComplete()) {
            order.markComplete();
        } else {
            order.markIncomplete();
        }

        return order;
    }
}

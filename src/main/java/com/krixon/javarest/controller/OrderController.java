package com.krixon.javarest.controller;

import com.krixon.javarest.domain.Order;
import com.krixon.javarest.repository.OrderRepository;
import com.krixon.javarest.resource.OrderResource;
import com.krixon.javarest.resource.OrderResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@ExposesResourceFor(Order.class)
@RequestMapping(value = "/order", produces = "application/json")
public class OrderController
{
    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderResourceMapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<OrderResource>> findAllOrders()
    {
        List<Order> orders = repository.all();

        return new ResponseEntity<>(mapper.toResourceCollection(orders), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<OrderResource> createOrder(@RequestBody OrderResource resource)
    {
        Order order = mapper.fromResource(resource);

        try {
            repository.add(order);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return new ResponseEntity<>(mapper.toResource(order), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<OrderResource> findOrderById(@PathVariable UUID id)
    {
        Optional<Order> order = repository.findById(id);

        if (order.isPresent()) {
            return new ResponseEntity<>(mapper.toResource(order.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID id)
    {
        boolean wasDeleted = repository.remove(id);
        HttpStatus responseStatus = wasDeleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(responseStatus);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<OrderResource> updateOrder(@PathVariable UUID id, @RequestBody OrderResource resource)
    {
        Order order = mapper.fromResource(resource);

        boolean wasUpdated = repository.update(order);

        if (wasUpdated) {
            return findOrderById(id);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
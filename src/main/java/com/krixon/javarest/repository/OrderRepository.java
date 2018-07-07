package com.krixon.javarest.repository;

import com.krixon.javarest.domain.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository extends InMemoryRepository<Order>
{
}

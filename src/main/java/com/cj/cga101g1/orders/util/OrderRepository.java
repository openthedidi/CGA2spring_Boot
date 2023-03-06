package com.cj.cga101g1.orders.util;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order,Integer> {
    @Query(value = "select * from cga101g1.orders where OrderNo = ?1",nativeQuery = true)
    Order findOrderByOrderNo(Integer orderNo);
}

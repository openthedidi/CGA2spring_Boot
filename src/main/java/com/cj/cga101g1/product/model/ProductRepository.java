package com.cj.cga101g1.product.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Query(nativeQuery = true, value = "select ProductNo,ProductName from product")
    List<String> showAllProductsName();
}

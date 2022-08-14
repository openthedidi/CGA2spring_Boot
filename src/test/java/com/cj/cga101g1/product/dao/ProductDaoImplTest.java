package com.cj.cga101g1.product.dao;

import com.cj.cga101g1.product.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductDaoImplTest {

    @Autowired
    private  ProductDao productDao;

    @Test
    public void findByPrimaryKey(){
        Product product= productDao.findByPrimaryKey(21001);

        assertNotNull(product);
        assertEquals(1600,product.getProductPrice());
        assertTrue(product.getProductState()>0);


    }

}
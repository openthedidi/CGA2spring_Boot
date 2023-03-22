package com.cj.cga101g1.orderdetail.service;

import com.cj.cga101g1.orderdetail.dao.OrderDetailDao;
import com.cj.cga101g1.orderdetail.util.OrderDetail;
import com.cj.cga101g1.orders.dao.OrderDao;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderDetailServiceImpTest {

    @MockBean
    private OrderDetailDao orderDetailDao;
    @Autowired
    private OrderDetail orderDetailMockVO;
    @Autowired
    private OrderDetailService orderDetailService;

    @Test
    void getAllDetailByOrderNo() {
        List<OrderDetail> list = new ArrayList<>();
        orderDetailMockVO.setOrderNo(24099);
        orderDetailMockVO.setProductNo(21002);
        list.add(orderDetailMockVO);
        Mockito.when(orderDetailDao.getAllDetailByOrderNo(24099)).thenReturn(list);
        List<OrderDetail> listResult = orderDetailService.getAllDetailByOrderNo(24099);
        assertEquals(1, listResult.size());
        assertEquals(24099,listResult.get(0).getOrderNo());

    }
}
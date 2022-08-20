package com.cj.cga101g1.orderdetail.service;

import com.cj.cga101g1.orderdetail.dao.OrderDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderDetailServiceImp implements OrderDetailService{

    @Autowired
    private OrderDetailDao orderDetailDao;


    @Override
    public Map<String, Object> showCaledCommentByProductNo(Integer productNo) {
        return orderDetailDao.showCaledCommentByProductNo(productNo);
    }
}

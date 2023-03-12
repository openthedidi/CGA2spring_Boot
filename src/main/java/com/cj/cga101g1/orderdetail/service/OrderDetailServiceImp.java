package com.cj.cga101g1.orderdetail.service;

import com.cj.cga101g1.member.dao.MemberDao;
import com.cj.cga101g1.member.util.Mem;
import com.cj.cga101g1.orderdetail.dao.OrderDetailDao;
import com.cj.cga101g1.orderdetail.util.CartDetail;
import com.cj.cga101g1.orderdetail.util.OrderDetail;
import com.cj.cga101g1.orders.dao.OrderDao;
import com.cj.cga101g1.orders.util.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDetailServiceImp implements OrderDetailService{

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private MemberDao memberDao;


    @Override
    public List<CartDetail> reduceCart(List<CartDetail> existCartList, String productNo, Integer productSales, Integer productTotalPrice, String productName) {
            CartDetail cartDetail = new CartDetail();
            cartDetail.setProductNo(productNo);
            cartDetail.setProductName(productName);
            cartDetail.setProductSales(productSales);
            cartDetail.setProductTotalPrice(productTotalPrice);

            CartDetail existCart = existCartList.get(existCartList.indexOf(cartDetail));
            Integer resultProductSales = existCart.getProductSales() - cartDetail.getProductSales();
            Integer resultTotalPrice = existCart.getProductTotalPrice() - cartDetail.getProductTotalPrice();
            if(resultProductSales == 0){
                existCartList.remove(existCart);
            }else {
                existCart.setProductSales(resultProductSales);
                existCart.setProductTotalPrice(resultTotalPrice);
            }
            return existCartList;
    }

    @Override
    public Map<String, Object> showCaledCommentByProductNo(Integer productNo) {
        return orderDetailDao.showCaledCommentByProductNo(productNo);
    }

    @Override
    public void createOne(Integer orderNo, OrderDetail orderDetail) {

    }

    /** session attribute : shoppingCart*/
    @Override
    public List<CartDetail> setShoppingCart(HttpSession session, String productNo, Integer productSales, Integer productTotalPrice, String productName) {
        List<CartDetail> cartDetailList =
                ((List<CartDetail>) session.getAttribute("shoppingCart") == null
                        ? new ArrayList<CartDetail>()
                        : (List<CartDetail>) session.getAttribute("shoppingCart"));

        /** 沒有購物車 **/
        if(cartDetailList.size() == 0){
            CartDetail cartDetail = new CartDetail().getCartDetail(productName,productNo,productSales,productTotalPrice);
            cartDetailList.add(cartDetail);
            session.setAttribute("shoppingCart",cartDetailList);
        }else{
            CartDetail newCarDetail = new CartDetail().getCartDetail(productName,productNo,productSales,productTotalPrice);
            /** 有購物車且有過相同商品 **/
            if(cartDetailList.contains(newCarDetail)){
                CartDetail oldCarDetail = cartDetailList.get(cartDetailList.indexOf(newCarDetail));
                Integer sales = oldCarDetail.getProductSales() + newCarDetail.getProductSales();
                Integer totalPrice = oldCarDetail.getProductTotalPrice() + newCarDetail.getProductTotalPrice();
                oldCarDetail.setProductSales(sales);
                oldCarDetail.setProductTotalPrice(totalPrice);
            }else{
                /** 有購物車但是沒有相同商品 **/
                cartDetailList.add(newCarDetail);
            }
        }


        return cartDetailList;
    }

    @Override
    public List<Object> showOneProductAllComments(Integer productNo) {
        List<Object> list = new ArrayList<>();
        List<OrderDetail> queryList = orderDetailDao.showOneProductAllComments(productNo);
        for(OrderDetail orderDetailVO : queryList) {
            Map<String,Object> map = new HashMap<>();
            map.put("commentCotent", orderDetailVO.getCommentCotent());
            map.put("commentStar", orderDetailVO.getCommentStar());
            map.put("commentTime", orderDetailVO.getCommentTime());
            Integer orderNo = orderDetailVO.getOrderNo();
            map.put("orderNo", orderNo);
            map.put("productNo", productNo);
            Integer memNo= orderDao.getMemNoByOrderNo(orderNo);
            String memAccount =memberDao.getMemAccountByMemNo(memNo);
            map.put("memAccount", memAccount);
            map.put("memPicURL","/mem/getMemPic?memNo="+memNo);
            list.add(map);
        }

        return list;
    }

    @Override
    public Map showProductCaledComment(Integer productNo) {
        List<OrderDetail> list = orderDetailDao.getCommentsInfosByOneProeduct(productNo);
        Integer count = list.size();
        Integer totalStars = 0 ;
        for(int i = 0 ; i < count ; i++ ){
            Integer stars = list.get(i).getCommentStar();
            totalStars += stars;
        }
        Integer avgStars = (int) Math.floor(totalStars/count);
        Map<String,Object> map = new HashMap<>();
        map.put("countComment", count);
        map.put("sumCommentStar", totalStars);
        map.put("avgCommentStar", avgStars);

        return map;
    }

    @Override
    public void addCart(List<CartDetail> existCartList, String productNo, Integer productSales, Integer productTotalPrice, String productName) {
        CartDetail cartDetail = new CartDetail();
        cartDetail.setProductNo(productNo);
        cartDetail.setProductName(productName);
        cartDetail.setProductSales(productSales);
        cartDetail.setProductTotalPrice(productTotalPrice);

        CartDetail existCart = existCartList.get(existCartList.indexOf(cartDetail));
        Integer resultProductSales = existCart.getProductSales() + cartDetail.getProductSales();
        Integer resultTotalPrice = existCart.getProductTotalPrice() + cartDetail.getProductTotalPrice();
        existCart.setProductSales(resultProductSales);
        existCart.setProductTotalPrice(resultTotalPrice);

    }

    @Override
    public void shoppingCartRemoveAll(List<CartDetail> existCartList, String productNo, Integer productSales, Integer productTotalPrice, String productName) {
        CartDetail cartDetail = new CartDetail();
        cartDetail.setProductNo(productNo);
        cartDetail.setProductName(productName);
        cartDetail.setProductSales(productSales);
        cartDetail.setProductTotalPrice(productTotalPrice);
        existCartList.remove(cartDetail);

    }

    @Override
    public void newOrderDetail(Integer newOrderNo, List<CartDetail> cartDetailList) {
        for (CartDetail cartDetail:cartDetailList) {
            Integer productTotalPrice = cartDetail.getProductTotalPrice();
            Integer sales = cartDetail.getProductSales();
            String productNo = cartDetail.getProductNo();
            orderDetailDao.newOrderDetail(newOrderNo, productTotalPrice, sales, productNo);
        }
    }

    @Override
    public List<OrderDetail> getAllDetailByOrderNo(Integer orderNo) {
        return orderDetailDao.getAllDetailByOrderNo(orderNo);
    }

    @Override
    public void addCommit(Integer productNo, Integer orderNo, String commentCotent, Integer commentStar) {
        orderDetailDao.addCommit(productNo,orderNo, commentCotent, commentStar);
    }
}

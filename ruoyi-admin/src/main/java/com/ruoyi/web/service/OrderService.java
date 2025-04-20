package com.ruoyi.web.service;

import java.util.List;

import com.ruoyi.pda.domain.Comment;
import com.ruoyi.pda.domain.DTO.CreateOrderDTO;
import com.ruoyi.pda.domain.DTO.OrderQuery;
import com.ruoyi.pda.domain.VO.OrderVO;

public interface OrderService {
    List<OrderVO> getList(OrderQuery orderQuery);
    
    OrderVO getDetail(long orderId);
    
    Comment getReview(long orderId);
    
    // 创建订单方法
    long createOrder(CreateOrderDTO createOrderDTO);
    
    // 支付订单方法
    boolean payOrder(long orderId, String payMethod);
    
    // 取消订单
    boolean cancelOrder(long orderId);
}

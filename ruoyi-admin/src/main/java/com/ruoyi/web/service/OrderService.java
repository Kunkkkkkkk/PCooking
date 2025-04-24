package com.ruoyi.web.service;

import java.util.List;

import com.ruoyi.pda.domain.Comment;
import com.ruoyi.pda.domain.DTO.CreateOrderDTO;
import com.ruoyi.pda.domain.DTO.OrderCommentDTO;
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

    void insertComment(OrderCommentDTO orderCommentDTO);

    /**
     * 查询订单列表
     * @param status 订单状态
     * @param chiefId 厨师ID，如果为null则查询所有未分配厨师的订单
     * @return 订单列表
     */
    List<OrderVO> getOrderList(String status, Long chiefId);
    //接单
    void accept(long orderId,long chiefId);

    //烹饪完成
    void cookComplete(long orderId);

    //模拟取餐
    void takeout(long orderId);

    //获取当前订单
    OrderVO getCurrentOrder();

    List<OrderVO> getList2(OrderQuery orderQuery);
}

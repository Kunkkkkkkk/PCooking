package com.ruoyi.web.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.pda.domain.Comment;
import com.ruoyi.pda.domain.Order;
import com.ruoyi.pda.domain.DTO.CreateOrderDTO;
import com.ruoyi.pda.domain.DTO.OrderCommentDTO;
import com.ruoyi.pda.domain.DTO.OrderItem;
import com.ruoyi.pda.domain.DTO.OrderQuery;
import com.ruoyi.pda.domain.VO.OrderVO;
import com.ruoyi.web.mapper.OrderItemMapper;
import com.ruoyi.web.mapper.OrderMapper;
import com.ruoyi.web.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderService orderService;

    @Override
    public List<OrderVO> getList(OrderQuery orderQuery) {
        // 先取消超时的未支付订单
        cancelTimeoutOrders();
        // 查询订单列表
        List<OrderVO> orderList = orderMapper.getList(orderQuery);
        
        // 为每个订单查询订单项
        if (orderList != null && !orderList.isEmpty()) {
            for (OrderVO orderVO : orderList) {
                // 查询订单项
                List<OrderItem> orderItems = orderItemMapper.selectByOrderId(orderVO.getOrderId());
                orderVO.setOrderItems(orderItems);
            }
        }
        
        return orderList;
    }
    
    @Override
    public OrderVO getDetail(long orderId) {
        OrderVO orderVO = orderMapper.getDetail(orderId);
        if (orderVO != null) {
            // 查询订单项
            List<OrderItem> orderItems = orderItemMapper.selectByOrderId(orderId);
            orderVO.setOrderItems(orderItems);
        }
        return orderVO;
    }
    
    @Override
    public Comment getReview(long orderId) {
        return orderMapper.getReview(orderId);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public long createOrder(CreateOrderDTO createOrderDTO) {
        // 创建主订单
        Order order = new Order();
        order.setUserId(createOrderDTO.getUserId());
        order.setChiefId(createOrderDTO.getChiefId());
        order.setPrice(createOrderDTO.getPrice());
        order.setStatus(createOrderDTO.getStatus());
        order.setCreateTime(DateUtil.date());
        order.setOrderTime(createOrderDTO.getOrderTime());
        order.setRemark(createOrderDTO.getRemark());
        
        // 插入主订单并获取订单ID
        orderMapper.insertOrder(order);
        long orderId = order.getOrderId();
        
        // 处理订单项
        if (createOrderDTO.getOrderItems() != null && !createOrderDTO.getOrderItems().isEmpty()) {
            for (CreateOrderDTO.OrderItemDTO item : createOrderDTO.getOrderItems()) {
                // 每个菜品创建一个订单项
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderId(orderId);
                orderItem.setDishId(item.getDishId());
                orderItem.setQuantity(item.getQuantity());
                orderItem.setPrice(item.getPrice());
                orderItem.setLackMaterial(item.getLackMaterial());
                
                // 插入订单项
                orderItemMapper.insertOrderItem(orderItem);
            }
        }
        
        return orderId;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean payOrder(long orderId, String payMethod) {
        try {
            Order order = new Order();
            order.setOrderId(orderId);
            int a = orderMapper.isChosenChef(orderId);
            if (a > 0) {
                order.setStatus("-3"); // 更新为待炒状态
            }else {
                order.setStatus("0"); // 更新为待接单状态
            }

            
            // 记录支付时间，可以根据需求添加
            // 记录支付方式，可以根据需求添加
            
            // 更新订单状态
            int rows = orderMapper.updateOrderStatus(order);
            return rows > 0;
        } catch (Exception e) {
            log.error("支付订单失败，订单ID: " + orderId, e);
            return false;
        }
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelOrder(long orderId) {
        try {
            // 取消订单，将状态设为已取消(-2)
            int rows = orderMapper.cancelOrder(orderId);
            return rows > 0;
        } catch (Exception e) {
            log.error("取消订单失败，订单ID: " + orderId, e);
            return false;
        }
    }
    
    /**
     * 取消超时未支付订单
     */
    private void cancelTimeoutOrders() {
        try {
            int i = orderMapper.cancelTimeoutOrders();

                    } catch (Exception e) {
            // 记录日志但不抛出异常
            log.error("取消超时未支付订单失败", e);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertComment(OrderCommentDTO orderCommentDTO) {
        String[] strings = orderCommentDTO.getDishIds().split(",");
        Long userId = SecurityUtils.getUserId();
        long commentId = 0;
        for (String dishId : strings) {
            orderMapper.insertComment(orderCommentDTO,dishId);
        }
        commentId = orderCommentDTO.getCommentId();
        orderMapper.updateOrderCommentId(orderCommentDTO.getOrderId(), commentId);
    }

    @Override
    public List<OrderVO> getOrderList(String status, Long chiefId) {
        // 构建查询参数
        Map<String, Object> params = new HashMap<>();
        List<OrderVO> orderList = new ArrayList<>();
            // 查询指定厨师的订单
        params.put("chiefId", chiefId);
        params.put("status", status);
        orderList = orderMapper.getOrderList(params);
        if (orderList != null && !orderList.isEmpty()) {
            for (OrderVO orderVO : orderList) {
                // 查询订单项
                List<OrderItem> orderItems = orderItemMapper.selectByOrderId(orderVO.getOrderId());
                orderVO.setOrderItems(orderItems);
            }
        }
        // 调用Mapper方法查询
        return orderList;
    }

    @Override
    public void accept(long orderId,long chiefId) {
        orderMapper.accept(orderId,chiefId);
    }

    @Override
    public void cookComplete(long orderId) {
        orderMapper.cookComplete(orderId);
    }
    @Override
    public void takeout(long orderId) {
        orderMapper.takeout(orderId);
    }

    @Override
    public OrderVO getCurrentOrder() {
        Long userId = SecurityUtils.getUserId();
        return orderMapper.getCurrentOrder(userId);
    }

    @Override
    public List<OrderVO> getList2(OrderQuery orderQuery) {
        return orderMapper.getList2(orderQuery);
    }

    @Override
    public long getUserIdByChiefId(long chiefId) {
        return orderMapper.getUserIdByChiefId(chiefId);
    }

    @Override
    public long getUserIdByOrderId(Long orderId) {
        return orderMapper.getUserIdByOrderId(orderId);
    }
}


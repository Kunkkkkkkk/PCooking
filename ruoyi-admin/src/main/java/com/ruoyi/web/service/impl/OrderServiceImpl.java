package com.ruoyi.web.service.impl;

import com.ruoyi.pda.domain.Comment;
import com.ruoyi.pda.domain.DTO.OrderQuery;
import com.ruoyi.pda.domain.Order;
import com.ruoyi.pda.domain.VO.OrderVO;
import com.ruoyi.web.mapper.OrderMapper;
import com.ruoyi.web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public List<OrderVO> getList(OrderQuery orderQuery) {
        return orderMapper.getList(orderQuery);
    }

    @Override
    public OrderVO getDetail(long orderId) {
        return orderMapper.getDetailById(orderId);
    }

    @Override
    public Comment getReview(long orderId) {
        return orderMapper.getReviewById(orderId);
    }
}

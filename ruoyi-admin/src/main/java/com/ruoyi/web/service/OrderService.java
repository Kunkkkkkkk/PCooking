package com.ruoyi.web.service;

import com.ruoyi.pda.domain.Comment;
import com.ruoyi.pda.domain.DTO.OrderQuery;
import com.ruoyi.pda.domain.Order;
import com.ruoyi.pda.domain.VO.OrderVO;

import java.util.List;

public interface OrderService {
    List<OrderVO> getList(OrderQuery orderQuery);
    OrderVO getDetail(long orderId);

    Comment getReview(long orderId);
}

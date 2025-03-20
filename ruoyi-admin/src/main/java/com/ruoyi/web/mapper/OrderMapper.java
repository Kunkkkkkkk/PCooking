package com.ruoyi.web.mapper;

import com.ruoyi.pda.domain.Comment;
import com.ruoyi.pda.domain.DTO.OrderQuery;
import com.ruoyi.pda.domain.Order;
import com.ruoyi.pda.domain.VO.OrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<OrderVO> getList(OrderQuery orderQuery);
    OrderVO getDetailById(long id);
    Comment getReviewById(long orderId);
}

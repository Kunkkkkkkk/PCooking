package com.ruoyi.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ruoyi.pda.domain.DTO.OrderItem;

/**
 * 订单项数据访问接口
 */
@Mapper
public interface OrderItemMapper {
    
    /**
     * 插入订单项记录
     * 
     * @param orderItem 订单项信息
     * @return 影响行数
     */
    int insertOrderItem(@Param("item") OrderItem orderItem);
    
    /**
     * 根据订单ID查询订单项
     * 
     * @param orderId 订单ID
     * @return 订单项列表
     */
    List<OrderItem> selectByOrderId(@Param("orderId") Long orderId);
}

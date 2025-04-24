package com.ruoyi.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ruoyi.pda.domain.Comment;
import com.ruoyi.pda.domain.Order;
import com.ruoyi.pda.domain.DTO.OrderCommentDTO;
import com.ruoyi.pda.domain.DTO.OrderQuery;
import com.ruoyi.pda.domain.VO.OrderVO;

/**
 * 订单数据访问接口
 */
@Mapper
public interface OrderMapper {
    
    /**
     * 查询订单列表
     * 
     * @param orderQuery 查询参数
     * @return 订单列表
     */
    List<OrderVO> getList(OrderQuery orderQuery);
    
    /**
     * 根据ID查询订单详情
     * 
     * @param orderId 订单ID
     * @return 订单详情
     */
    OrderVO getDetail(long orderId);
    
    /**
     * 查询订单评论
     * 
     * @param orderId 订单ID
     * @return 评论信息
     */
    Comment getReview(long orderId);
    
    /**
     * 插入订单记录
     * 
     * @param order 订单信息
     * @return 影响行数
     */
    int insertOrder(Order order);
    
    /**
     * 取消超时未支付订单
     * 
     * @return 影响的行数
     */
    int cancelTimeoutOrders();
    
    /**
     * 更新订单状态
     * 
     * @param orderId 订单ID
     * @param status 订单状态
     * @return 影响的行数
     */
    int updateOrderStatus(Order order);
    
    /**
     * 取消订单
     * 
     * @param orderId 订单ID
     * @return 影响的行数
     */
    int cancelOrder(long orderId);

    void insertComment(@Param("orderCommentDTO") OrderCommentDTO orderCommentDTO, @Param("dishId") String dishId);

    /**
     * 根据参数查询订单列表
     * @param params 查询参数，包含status、chiefId、unassigned等
     * @return 订单列表
     */
    List<OrderVO> getOrderList(Map<String, Object> params);


    void accept(@Param("orderId")long orderId,@Param("chiefId")long chiefId);


    List<OrderVO>getUnCookedList(long chiefId);
    @Select("SELECT count(1) from master_order where order_id=#{orderId} and chief_id is not null")
    int isChosenChef(long orderId);

    @Update("UPDATE master_order SET status='1',cooked_time=NOW() WHERE order_id=#{orderId}")
    void cookComplete(long orderId);

    @Update("UPDATE master_order SET status='2' WHERE order_id=#{orderId}")
    void takeout(long orderId);

    @Update("UPDATE master_order SET comment_id=#{commentId} WHERE order_id=#{orderId}")
    void updateOrderCommentId(@Param("orderId")long orderId, @Param("commentId")long commentId);

    OrderVO getCurrentOrder(@Param("userId")long userId);

    List<OrderVO> getList2(OrderQuery orderQuery);
}

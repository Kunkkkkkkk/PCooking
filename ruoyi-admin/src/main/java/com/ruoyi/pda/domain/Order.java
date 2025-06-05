package com.ruoyi.pda.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.ruoyi.pda.domain.DTO.OrderItem;
import lombok.Data;

/**
 * 订单实体类
 */
@Data
public class Order {
    /** 订单ID */
    private Long orderId;
    
    /** 厨师ID */
    private Long chiefId;
    
    /** 用户ID */
    private Long userId;
    
    /** 订单总价 */
    private Double price;
    
    /** 状态 -1为待支付，0为待接单，1为待取餐，2为已完成 */
    private String status;
    
    /** 创建时间 */
    private Date createTime;
    
    /** 预约时间 */
    private Date orderTime;
    
    /** 订单备注 */
    private String remark;
    
    /** 评价ID */
    private Long commentId;
    
    /** 评分 */
    private Double rating;
    
    /** 订单项列表（非数据库字段） */
    private List<OrderItem> orderItems;

}


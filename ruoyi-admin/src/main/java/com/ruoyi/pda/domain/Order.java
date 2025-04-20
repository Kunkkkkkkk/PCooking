package com.ruoyi.pda.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.ruoyi.pda.domain.DTO.OrderItem;

/**
 * 订单实体类
 */
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
    private LocalDateTime createTime;
    
    /** 预约时间 */
    private LocalDateTime orderTime;
    
    /** 订单备注 */
    private String remark;
    
    /** 评价ID */
    private Long commentId;
    
    /** 评分 */
    private Double rating;
    
    /** 订单项列表（非数据库字段） */
    private List<OrderItem> orderItems;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getChiefId() {
        return chiefId;
    }

    public void setChiefId(Long chiefId) {
        this.chiefId = chiefId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}


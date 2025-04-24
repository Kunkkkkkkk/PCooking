package com.ruoyi.pda.domain.VO;

import java.time.LocalDateTime;
import java.util.List;

import com.ruoyi.pda.domain.DTO.OrderItem;

/**
 * 订单视图对象
 */
public class OrderVO {
    private Long orderId;
    private Long chiefId;
    private Long userId;
    private Double price;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime orderTime;
    private Long commentId;
    private String chiefName;
    private String userName;
    private String remark;
    private String userAvatar; 
    // 厨师真实姓名
    private String chiefRealName;
    private LocalDateTime cookedTime;
    // 厨师头像
    private String chiefAvatar;


    public void setCookedTime(LocalDateTime cookedTime) {
        this.cookedTime = cookedTime;
    }
    public LocalDateTime getCookedTime() {
        return cookedTime;
    }
    // 订单项列表
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

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getChiefName() {
        return chiefName;
    }

    public void setChiefName(String chiefName) {
        this.chiefName = chiefName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    
    public String getChiefRealName() {
        return chiefRealName;
    }

    public void setChiefRealName(String chiefRealName) {
        this.chiefRealName = chiefRealName;
    }

    public String getChiefAvatar() {
        return chiefAvatar;
    }

    public void setChiefAvatar(String chiefAvatar) {
        this.chiefAvatar = chiefAvatar;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
}

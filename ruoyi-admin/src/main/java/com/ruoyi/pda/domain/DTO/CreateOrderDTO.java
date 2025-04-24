package com.ruoyi.pda.domain.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class CreateOrderDTO {
    private Long userId;
    private Long dishId;
    private Double price;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime orderTime;
    private String remark;
    private Long chiefId;
    private String lackMaterial;
    private List<OrderItemDTO> orderItems;

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

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
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

    public String getLackMaterial() {
        return lackMaterial;
    }

    public void setLackMaterial(String lackMaterial) {
        this.lackMaterial = lackMaterial;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    // 订单项内部类
    public static class OrderItemDTO {
        private Long dishId;
        private Integer quantity;
        private Double price;
        private String lackMaterial;

        public Long getDishId() {
            return dishId;
        }

        public void setDishId(Long dishId) {
            this.dishId = dishId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public String getLackMaterial() {
            return lackMaterial;
        }

        public void setLackMaterial(String lackMaterial) {
            this.lackMaterial = lackMaterial;
        }
    }
} 
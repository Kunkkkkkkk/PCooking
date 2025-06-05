package com.ruoyi.pda.domain.DTO;

import java.util.Date;
import java.util.List;

import lombok.Data;
@Data
public class CreateOrderDTO {
    private Long userId;
    private Long dishId;
    private Double price;
    private String status;
    private Date createTime;
    private Date orderTime;
    private String remark;
    private Long chiefId;
    private String lackMaterial;
    private List<OrderItemDTO> orderItems;

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
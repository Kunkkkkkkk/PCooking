package com.ruoyi.pda.domain.DTO;


// 订单项实体类
public class OrderItem {
    private Long id;
    private Long orderId;
    private Long dishId;
    private Integer quantity;
    private Double price;
    private String lackMaterial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

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
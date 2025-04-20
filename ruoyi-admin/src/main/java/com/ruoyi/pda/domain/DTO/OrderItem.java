package com.ruoyi.pda.domain.DTO;

/**
 * 订单项实体类
 */
public class OrderItem {
    /** 订单项ID */
    private Long id;
    
    /** 订单ID */
    private Long orderId;
    
    /** 菜品ID */
    private Long dishId;
    
    /** 数量 */
    private Integer quantity;
    
    /** 价格 */
    private Double price;
    
    /** 自备食材列表（逗号分隔的ID） */
    private String lackMaterial;
    
    /** 菜品名称 */
    private String dishName;
    
    /** 菜品图片 */
    private String dishImage;
    
    /** 菜品图片URL */
    private String dishImageUrl;

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
    
    public String getDishName() {
        return dishName;
    }
    
    public void setDishName(String dishName) {
        this.dishName = dishName;
    }
    
    public String getDishImage() {
        return dishImage;
    }
    
    public void setDishImage(String dishImage) {
        this.dishImage = dishImage;
    }
    
    public String getDishImageUrl() {
        return dishImageUrl;
    }
    
    public void setDishImageUrl(String dishImageUrl) {
        this.dishImageUrl = dishImageUrl;
    }
}
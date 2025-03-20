package com.ruoyi.pda.domain;

import lombok.Data;

@Data
public class DishMaterial {
    private Integer id;
    private Integer dishId;
    private Integer ingredientId;
    private String name;
    private int quantity;
    private String unit;
    private String isRequired;
    private double extraPrice;
}

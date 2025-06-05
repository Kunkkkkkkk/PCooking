package com.ruoyi.pda.domain;

import java.util.List;

import lombok.Data;

@Data
public class Dishes {
    private Integer dishId;
    private String name;
    private long typeDictCode;
    private String typeName;
    private double price;
    private String status ;
    private String image;
    private List<DishMaterial> dishMaterials;
    private String tags;
    private String modifyTime;
    private String discount;
}

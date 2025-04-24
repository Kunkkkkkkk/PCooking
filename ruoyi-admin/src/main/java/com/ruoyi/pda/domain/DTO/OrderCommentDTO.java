package com.ruoyi.pda.domain.DTO;

import java.util.List;

import lombok.Data;

@Data
public class OrderCommentDTO {
    private long orderId;
    private String content;
    private double rating;
    private List<String> imageUrlsList;
    //处理之后的url，可以直接插入
    private String imagesUrls;
    //相关的dishId
    private String dishIds;
    //评论ID (用于存储自动生成的ID)
    private long commentId;
}

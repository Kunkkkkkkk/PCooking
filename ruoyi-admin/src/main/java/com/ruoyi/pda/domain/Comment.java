package com.ruoyi.pda.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Comment {
    private long commentId;
    private String content;
    private String imgUrls;
    private List<String> imgUrlsList;
    private double rating;
    private LocalDateTime createTime;
    private String userAvatar;
}

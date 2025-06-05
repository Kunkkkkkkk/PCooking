package com.ruoyi.pda.domain.DTO;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class OrderQuery{
    private long orderId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime; // 开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;   // 结束时间
    private String status;
    private long userId;
    private String userName;
}

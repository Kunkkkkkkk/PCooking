package com.ruoyi.pda.domain.VO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
//TODO 缺少预约时间
public class OrderVO {
    private long orderId;
    private long chiefId;
    private long userId;
    private long dishId;
    private double price;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime finishTime;
    private String chiefName;
    private String userName;
    private String dishName;
    private String content;
}

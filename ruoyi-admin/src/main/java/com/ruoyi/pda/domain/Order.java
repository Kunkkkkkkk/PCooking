package com.ruoyi.pda.domain;

import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Order {
    private long orderId;
    private long chiefId;
    private long userId;
    private long dishId;
    private double price;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime finishTime;
    private long commentId;
    private double rating;
}

package com.ruoyi.pda.domain.VO;

import lombok.Data;

@Data
public class OrderHistory{
    private String date; // 日期，格式 "YYYY-MM-DD"
    private Integer orderCount; // 当天订单数
}

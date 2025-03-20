package com.ruoyi.pda.domain.VO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ChiefVO {
    private long id;
    private String name;
    private String phone;
    private long role;
    private double rating;
    private String status;
    private String role_name;
    private LocalDateTime joinTime;
    private int orderNum;
    private double averageTime;
    private List<OrderHistory> orderHistory; // 近30天订单历史
}

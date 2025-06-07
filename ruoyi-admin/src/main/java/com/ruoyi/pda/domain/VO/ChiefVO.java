package com.ruoyi.pda.domain.VO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ChiefVO {
    private long id;
    private String name;
    private String phone;
    private long role;
    private double rating; // 平均评分
    private String status;
    private String role_name;
    private LocalDateTime joinTime;
    private int orderNum; // 总完成订单数
    private double averageTime;
    private String avatar;
    //真实照片（展示用）
    private String realPhoto;
    //介绍信息
    private String description;
    //0无 1银牌厨师  2金牌厨师
    private String isRecommend;
    // --- 新增绩效统计字段 ---
    private BigDecimal totalRevenue; // 总营业额 (使用 BigDecimal 保证精度)
    private int ordersLast30Days; // 近30天订单数
    private BigDecimal revenueLast30Days; // 近30天营业额

    // --- 图表数据 ---
    // Map<String, Integer> String 是日期 (YYYY-MM-DD), Integer 是订单数
    private Map<String, Integer> dailyOrderCounts; // 近30天每日订单数

    // 保留 orderHistory 但修改注释，如果其他地方仍在使用
    private List<OrderHistory> orderHistory; // 订单历史记录 (具体用途待确认)
}

package com.ruoyi.web.service;

import java.util.Map;

public interface DashboardService {
    
    /**
     * 获取仪表盘核心指标
     */
    Map<String, Object> getDashboardMetrics(String timeRange, String startDate, String endDate);
    
    /**
     * 获取订单趋势数据
     */
    Map<String, Object> getOrderTrend(String timeRange, String startDate, String endDate);
    
    /**
     * 获取订单状态分布
     */
    Map<String, Object> getOrderStatusDistribution();
    
    /**
     * 获取热门菜品排行
     */
    Map<String, Object> getPopularDishes(String timeRange, String startDate, String endDate);
    
    /**
     * 获取厨师工作负载
     */
    Map<String, Object> getChefWorkload(String timeRange, String startDate, String endDate);

    /**
     * 获取订单完成情况
     */
    Map<String, Object> getOrderCompletion(String timeRange, String startDate, String endDate);

    /**
     * 获取高峰时段分析
     */
    Map<String, Object> getPeakHours(String timeRange, String startDate, String endDate);
} 
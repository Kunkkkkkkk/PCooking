package com.ruoyi.web.service;

import java.util.Map;

/**
 * 数据仪表盘服务接口
 * 
 * @author kun
 */
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
    Map<String, Object> getOrderStatusDistribution(String timeRange, String startDate, String endDate);
    
    /**
     * 获取热门菜品排行
     */
    Map<String, Object> getPopularDishes(String timeRange, String startDate, String endDate);
    
    /**
     * 获取厨师工作负载
     */
    Map<String, Object> getChefWorkload(String timeRange, String startDate, String endDate);

    /**
     * 获取用户增长趋势
     */
    Map<String, Object> getUserGrowth(String timeRange, String startDate, String endDate);

    /**
     * 获取营收分析
     */
    Map<String, Object> getRevenueAnalysis(String timeRange, String startDate, String endDate);

    /**
     * 获取厨师申请统计
     */
    Map<String, Object> getChefApplicationStats(String timeRange, String startDate, String endDate);

    /**
     * 获取订单高峰时段分析
     */
    Map<String, Object> getPeakHoursAnalysis(String timeRange, String startDate, String endDate);

    /**
     * 获取菜品类别分析
     */
    Map<String, Object> getDishCategoryAnalysis(String timeRange, String startDate, String endDate);

    /**
     * 获取用户行为分析
     */
    Map<String, Object> getUserBehaviorAnalysis(String timeRange, String startDate, String endDate);

    /**
     * 获取厨师绩效排行
     */
    Map<String, Object> getChefPerformance(String timeRange, String startDate, String endDate);
} 
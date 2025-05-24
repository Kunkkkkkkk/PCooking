package com.ruoyi.web.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.web.mapper.DashboardMapper;
import com.ruoyi.web.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DashboardMapper dashboardMapper;

    @Override
    public Map<String, Object> getDashboardMetrics(String timeRange, String startDate, String endDate) {
        Map<String, Object> metrics = new HashMap<>();
        
        // 获取订单统计
        Map<String, Object> orderStats = dashboardMapper.getOrderStats(timeRange, startDate, endDate);
        metrics.put("totalOrders", orderStats.get("totalOrders"));
        metrics.put("todayOrders", orderStats.get("todayOrders"));
        metrics.put("totalRevenue", orderStats.get("totalRevenue"));
        metrics.put("todayRevenue", orderStats.get("todayRevenue"));
        
        // 获取用户统计
        Map<String, Object> userStats = dashboardMapper.getUserStats();
        metrics.put("totalUsers", userStats.get("totalUsers"));
        metrics.put("newUsers", userStats.get("newUsers"));
        
        // 获取厨师统计
        Map<String, Object> chefStats = dashboardMapper.getChefStats();
        metrics.put("totalChefs", chefStats.get("totalChefs"));
        metrics.put("pendingChefs", chefStats.get("pendingChefs"));
        
        // 计算增长率
        metrics.put("orderGrowth", calculateGrowthRate(
            ((Number)orderStats.get("previousOrders")).doubleValue(), 
            ((Number)orderStats.get("currentOrders")).doubleValue()));
            
        metrics.put("revenueGrowth", calculateGrowthRate(
            ((BigDecimal)orderStats.get("previousRevenue")).doubleValue(), 
            ((BigDecimal)orderStats.get("currentRevenue")).doubleValue()));
            
        metrics.put("userGrowth", calculateGrowthRate(
            ((Number)userStats.get("previousUsers")).doubleValue(), 
            ((Number)userStats.get("currentUsers")).doubleValue()));
        
        return metrics;
    }

    @Override
    public Map<String, Object> getOrderTrend(String timeRange, String startDate, String endDate) {
        List<Map<String, Object>> trend = dashboardMapper.getOrderTrend(timeRange, startDate, endDate);
        Map<String, Object> result = new HashMap<>();
        result.put("trend", trend);
        return result;
    }

    @Override
    public Map<String, Object> getOrderStatusDistribution() {
        List<Map<String, Object>> distribution = dashboardMapper.getOrderStatusDistribution();
        Map<String, Object> result = new HashMap<>();
        result.put("distribution", distribution);
        return result;
    }

    @Override
    public Map<String, Object> getPopularDishes(String timeRange, String startDate, String endDate) {
        List<Map<String, Object>> dishes = dashboardMapper.getPopularDishes(timeRange, startDate, endDate);
        Map<String, Object> result = new HashMap<>();
        result.put("dishes", dishes);
        return result;
    }

    @Override
    public Map<String, Object> getChefWorkload(String timeRange, String startDate, String endDate) {
        List<Map<String, Object>> workload = dashboardMapper.getChefWorkload(timeRange, startDate, endDate);
        Map<String, Object> result = new HashMap<>();
        result.put("workload", workload);
        return result;
    }

    @Override
    public Map<String, Object> getOrderCompletion(String timeRange, String startDate, String endDate) {
        List<Map<String, Object>> completion = dashboardMapper.getOrderCompletion(timeRange, startDate, endDate);
        Map<String, Object> result = new HashMap<>();
        result.put("completion", completion);
        return result;
    }

    @Override
    public Map<String, Object> getPeakHours(String timeRange, String startDate, String endDate) {
        List<Map<String, Object>> peak = dashboardMapper.getPeakHours(timeRange, startDate, endDate);
        Map<String, Object> result = new HashMap<>();
        result.put("peak", peak);
        return result;
    }

    private double calculateGrowthRate(double previous, double current) {
        if (previous == 0) {
            return current > 0 ? 100 : 0;
        }
        return ((current - previous) / previous) * 100;
    }
}
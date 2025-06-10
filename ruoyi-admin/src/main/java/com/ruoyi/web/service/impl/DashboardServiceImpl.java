package com.ruoyi.web.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.web.mapper.DashboardMapper;
import com.ruoyi.web.service.DashboardService;

/**
 * 数据仪表盘服务实现类
 * 
 * @author kun
 */
@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DashboardMapper dashboardMapper;

    @Override
    public Map<String, Object> getDashboardMetrics(String timeRange, String startDate, String endDate) {
        Map<String, Object> metrics = new HashMap<>();
        
        // 获取订单统计
        Map<String, Object> orderStats = dashboardMapper.getOrderStats(timeRange, startDate, endDate);
        
        // 使用按时间范围过滤的数据作为主要显示
        metrics.put("totalOrders", orderStats.get("filteredOrders"));
        metrics.put("todayOrders", orderStats.get("todayOrders"));
        metrics.put("orderGrowth", calculateGrowthRate(
            getDoubleValue(orderStats.get("previousOrders")), 
            getDoubleValue(orderStats.get("currentOrders"))));
        
        // 添加历史总数作为补充信息
        metrics.put("allTimeOrders", orderStats.get("allTimeOrders"));
        
        // 计算完成率（基于过滤后的数据）
        Long completedOrders = getLongValue(orderStats.get("completedOrders"));
        Long filteredOrders = getLongValue(orderStats.get("filteredOrders"));
        double completionRate = filteredOrders > 0 ? (completedOrders * 100.0 / filteredOrders) : 0;
        metrics.put("completionRate", completionRate);
        
        // 获取营收统计（使用按时间范围过滤的数据）
        metrics.put("totalRevenue", orderStats.get("filteredRevenue"));
        metrics.put("todayRevenue", orderStats.get("todayRevenue"));
        metrics.put("revenueGrowth", calculateGrowthRate(
            getBigDecimalValue(orderStats.get("previousRevenue")), 
            getBigDecimalValue(orderStats.get("currentRevenue"))));
        
        // 添加历史总营收作为补充信息
        metrics.put("allTimeRevenue", orderStats.get("allTimeRevenue"));
        
        // 计算平均订单价值（基于过滤后的数据）
        BigDecimal filteredRevenue = getBigDecimalFromValue(orderStats.get("filteredRevenue"));
        BigDecimal avgOrderValue = filteredOrders > 0 ? 
            filteredRevenue.divide(new BigDecimal(filteredOrders), 2, BigDecimal.ROUND_HALF_UP) : 
            BigDecimal.ZERO;
        metrics.put("avgOrderValue", avgOrderValue);
        
        // 获取用户统计
        Map<String, Object> userStats = dashboardMapper.getUserStats(timeRange, startDate, endDate);
        metrics.put("totalUsers", userStats.get("totalUsers"));
        metrics.put("newUsers", userStats.get("newUsers"));
        metrics.put("activeUsers", userStats.get("activeUsers"));
        metrics.put("userGrowth", calculateGrowthRate(
            getDoubleValue(userStats.get("previousUsers")), 
            getDoubleValue(userStats.get("currentUsers"))));
        
        // 获取厨师统计
        Map<String, Object> chefStats = dashboardMapper.getChefStats();
        metrics.put("totalChefs", chefStats.get("totalChefs"));
        metrics.put("pendingApplications", chefStats.get("pendingApplications"));
        metrics.put("goldChefs", chefStats.get("goldChefs"));
        metrics.put("silverChefs", chefStats.get("silverChefs"));
        
        return metrics;
    }

    @Override
    public Map<String, Object> getOrderTrend(String timeRange, String startDate, String endDate) {
        List<Map<String, Object>> trend = dashboardMapper.getOrderTrend(timeRange, startDate, endDate);
        Map<String, Object> result = new HashMap<>();
        result.put("dates", trend.stream().map(item -> item.get("date")).toArray());
        result.put("counts", trend.stream().map(item -> item.get("count")).toArray());
        result.put("revenues", trend.stream().map(item -> item.get("revenue")).toArray());
        return result;
    }

    @Override
    public Map<String, Object> getOrderStatusDistribution(String timeRange, String startDate, String endDate) {
        List<Map<String, Object>> distribution = dashboardMapper.getOrderStatusDistribution(timeRange, startDate, endDate);
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
    public Map<String, Object> getUserGrowth(String timeRange, String startDate, String endDate) {
        List<Map<String, Object>> growth = dashboardMapper.getUserGrowth(timeRange, startDate, endDate);
        Map<String, Object> result = new HashMap<>();
        result.put("dates", growth.stream().map(item -> item.get("date")).toArray());
        result.put("newUsers", growth.stream().map(item -> item.get("newUsers")).toArray());
        return result;
    }

    @Override
    public Map<String, Object> getRevenueAnalysis(String timeRange, String startDate, String endDate) {
        List<Map<String, Object>> analysis = dashboardMapper.getRevenueAnalysis(timeRange, startDate, endDate);
        Map<String, Object> result = new HashMap<>();
        result.put("analysis", analysis);
        return result;
    }

    @Override
    public Map<String, Object> getChefApplicationStats(String timeRange, String startDate, String endDate) {
        List<Map<String, Object>> stats = dashboardMapper.getChefApplicationStats(timeRange, startDate, endDate);
        Map<String, Object> result = new HashMap<>();
        result.put("applications", stats);
        return result;
    }

    @Override
    public Map<String, Object> getPeakHoursAnalysis(String timeRange, String startDate, String endDate) {
        List<Map<String, Object>> hours = dashboardMapper.getPeakHoursAnalysis(timeRange, startDate, endDate);
        Map<String, Object> result = new HashMap<>();
        result.put("hours", hours);
        return result;
    }

    @Override
    public Map<String, Object> getDishCategoryAnalysis(String timeRange, String startDate, String endDate) {
        List<Map<String, Object>> categories = dashboardMapper.getDishCategoryAnalysis(timeRange, startDate, endDate);
        Map<String, Object> result = new HashMap<>();
        result.put("categories", categories);
        return result;
    }

    @Override
    public Map<String, Object> getUserBehaviorAnalysis(String timeRange, String startDate, String endDate) {
        List<Map<String, Object>> behavior = dashboardMapper.getUserBehaviorAnalysis(timeRange, startDate, endDate);
        Map<String, Object> result = new HashMap<>();
        result.put("behavior", behavior);
        return result;
    }

    @Override
    public Map<String, Object> getChefPerformance(String timeRange, String startDate, String endDate) {
        List<Map<String, Object>> performance = dashboardMapper.getChefPerformance(timeRange, startDate, endDate);
        Map<String, Object> result = new HashMap<>();
        result.put("performance", performance);
        return result;
    }

    /**
     * 计算增长率
     */
    private double calculateGrowthRate(double previous, double current) {
        if (previous == 0) {
            return current > 0 ? 100 : 0;
        }
        return ((current - previous) / previous) * 100;
    }

    /**
     * 安全获取Double值
     */
    private double getDoubleValue(Object value) {
        if (value == null) return 0.0;
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        return 0.0;
    }

    /**
     * 安全获取BigDecimal值
     */
    private double getBigDecimalValue(Object value) {
        if (value == null) return 0.0;
        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).doubleValue();
        }
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        return 0.0;
    }

    /**
     * 安全获取Long值
     */
    private Long getLongValue(Object value) {
        if (value == null) return 0L;
        if (value instanceof Number) {
            return ((Number) value).longValue();
        }
        return 0L;
    }

    /**
     * 安全获取BigDecimal对象
     */
    private BigDecimal getBigDecimalFromValue(Object value) {
        if (value == null) return BigDecimal.ZERO;
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }
        if (value instanceof Number) {
            return BigDecimal.valueOf(((Number) value).doubleValue());
        }
        return BigDecimal.ZERO;
    }
}
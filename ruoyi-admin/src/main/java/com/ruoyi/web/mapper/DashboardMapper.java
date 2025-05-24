package com.ruoyi.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DashboardMapper {
    
    /**
     * 获取订单统计数据
     */
    Map<String, Object> getOrderStats(@Param("timeRange") String timeRange, 
                                    @Param("startDate") String startDate, 
                                    @Param("endDate") String endDate);
    
    /**
     * 获取用户统计数据
     */
    Map<String, Object> getUserStats();
    
    /**
     * 获取厨师统计数据
     */
    Map<String, Object> getChefStats();
    
    /**
     * 获取订单趋势数据
     */
    List<Map<String, Object>> getOrderTrend(@Param("timeRange") String timeRange, 
                                          @Param("startDate") String startDate, 
                                          @Param("endDate") String endDate);
    
    /**
     * 获取订单状态分布
     */
    List<Map<String, Object>> getOrderStatusDistribution();
    
    /**
     * 获取热门菜品排行
     */
    List<Map<String, Object>> getPopularDishes(@Param("timeRange") String timeRange, 
                                             @Param("startDate") String startDate, 
                                             @Param("endDate") String endDate);
    
    /**
     * 获取厨师工作负载
     */
    List<Map<String, Object>> getChefWorkload(@Param("timeRange") String timeRange, 
                                            @Param("startDate") String startDate, 
                                            @Param("endDate") String endDate);

    /**
     * 获取订单完成情况
     */
    List<Map<String, Object>> getOrderCompletion(@Param("timeRange") String timeRange, 
                                               @Param("startDate") String startDate, 
                                               @Param("endDate") String endDate);

    /**
     * 获取高峰时段分析
     */
    List<Map<String, Object>> getPeakHours(@Param("timeRange") String timeRange, 
                                         @Param("startDate") String startDate, 
                                         @Param("endDate") String endDate);
}
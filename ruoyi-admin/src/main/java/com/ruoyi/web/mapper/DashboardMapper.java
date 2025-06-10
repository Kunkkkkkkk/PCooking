package com.ruoyi.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 数据仪表盘数据访问层
 * 
 * @author kun
 */
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
    Map<String, Object> getUserStats(@Param("timeRange") String timeRange, 
                                   @Param("startDate") String startDate, 
                                   @Param("endDate") String endDate);
    
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
    List<Map<String, Object>> getOrderStatusDistribution(@Param("timeRange") String timeRange, 
                                                        @Param("startDate") String startDate, 
                                                        @Param("endDate") String endDate);
    
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
     * 获取用户增长趋势
     */
    List<Map<String, Object>> getUserGrowth(@Param("timeRange") String timeRange, 
                                          @Param("startDate") String startDate, 
                                          @Param("endDate") String endDate);

    /**
     * 获取营收分析
     */
    List<Map<String, Object>> getRevenueAnalysis(@Param("timeRange") String timeRange, 
                                               @Param("startDate") String startDate, 
                                               @Param("endDate") String endDate);

    /**
     * 获取厨师申请统计
     */
    List<Map<String, Object>> getChefApplicationStats(@Param("timeRange") String timeRange, 
                                                     @Param("startDate") String startDate, 
                                                     @Param("endDate") String endDate);

    /**
     * 获取订单高峰时段分析
     */
    List<Map<String, Object>> getPeakHoursAnalysis(@Param("timeRange") String timeRange, 
                                                  @Param("startDate") String startDate, 
                                                  @Param("endDate") String endDate);

    /**
     * 获取菜品类别分析
     */
    List<Map<String, Object>> getDishCategoryAnalysis(@Param("timeRange") String timeRange, 
                                                     @Param("startDate") String startDate, 
                                                     @Param("endDate") String endDate);

    /**
     * 获取用户行为分析
     */
    List<Map<String, Object>> getUserBehaviorAnalysis(@Param("timeRange") String timeRange, 
                                                     @Param("startDate") String startDate, 
                                                     @Param("endDate") String endDate);

    /**
     * 获取厨师绩效排行
     */
    List<Map<String, Object>> getChefPerformance(@Param("timeRange") String timeRange, 
                                               @Param("startDate") String startDate, 
                                               @Param("endDate") String endDate);
}
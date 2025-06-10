package com.ruoyi.web.controller.dashboard;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.web.service.DashboardService;

/**
 * 数据仪表盘控制器
 * 
 * @author kun
 */
@RestController
@RequestMapping("/system/dashboard")
public class DashboardController extends BaseController {
    
    @Autowired
    private DashboardService dashboardService;

    /**
     * 获取仪表盘核心指标数据
     */
    @GetMapping("/metrics")
    public AjaxResult getMetrics(
            @RequestParam(required = false) String timeRange,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        
        Map<String, Object> data = dashboardService.getDashboardMetrics(timeRange, startDate, endDate);
        return AjaxResult.success(data);
    }

    /**
     * 获取订单趋势数据
     */
    @GetMapping("/order-trend")
    public AjaxResult getOrderTrend(
            @RequestParam(required = false) String timeRange,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        
        Map<String, Object> data = dashboardService.getOrderTrend(timeRange, startDate, endDate);
        return AjaxResult.success(data);
    }

    /**
     * 获取订单状态分布
     */
    @GetMapping("/order-status")
    public AjaxResult getOrderStatusDistribution(
            @RequestParam(required = false) String timeRange,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        
        Map<String, Object> data = dashboardService.getOrderStatusDistribution(timeRange, startDate, endDate);
        return AjaxResult.success(data);
    }

    /**
     * 获取热门菜品排行
     */
    @GetMapping("/popular-dishes")
    public AjaxResult getPopularDishes(
            @RequestParam(required = false) String timeRange,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        
        Map<String, Object> data = dashboardService.getPopularDishes(timeRange, startDate, endDate);
        return AjaxResult.success(data);
    }

    /**
     * 获取厨师工作负载分析
     */
    @GetMapping("/chef-workload")
    public AjaxResult getChefWorkload(
            @RequestParam(required = false) String timeRange,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        
        Map<String, Object> data = dashboardService.getChefWorkload(timeRange, startDate, endDate);
        return AjaxResult.success(data);
    }

    /**
     * 获取用户增长趋势
     */
    @GetMapping("/user-growth")
    public AjaxResult getUserGrowth(
            @RequestParam(required = false) String timeRange,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        
        Map<String, Object> data = dashboardService.getUserGrowth(timeRange, startDate, endDate);
        return AjaxResult.success(data);
    }

    /**
     * 获取营收分析
     */
    @GetMapping("/revenue-analysis")
    public AjaxResult getRevenueAnalysis(
            @RequestParam(required = false) String timeRange,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        
        Map<String, Object> data = dashboardService.getRevenueAnalysis(timeRange, startDate, endDate);
        return AjaxResult.success(data);
    }

    /**
     * 获取厨师应用审核数据
     */
    @GetMapping("/chef-applications")
    public AjaxResult getChefApplicationStats(
            @RequestParam(required = false) String timeRange,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        
        Map<String, Object> data = dashboardService.getChefApplicationStats(timeRange, startDate, endDate);
        return AjaxResult.success(data);
    }

    /**
     * 获取订单高峰时段分析
     */
    @GetMapping("/peak-hours")
    public AjaxResult getPeakHoursAnalysis(
            @RequestParam(required = false) String timeRange,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        
        Map<String, Object> data = dashboardService.getPeakHoursAnalysis(timeRange, startDate, endDate);
        return AjaxResult.success(data);
    }

    /**
     * 获取菜品类别分析
     */
    @GetMapping("/dish-categories")
    public AjaxResult getDishCategoryAnalysis(
            @RequestParam(required = false) String timeRange,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        
        Map<String, Object> data = dashboardService.getDishCategoryAnalysis(timeRange, startDate, endDate);
        return AjaxResult.success(data);
    }

    /**
     * 获取用户行为分析
     */
    @GetMapping("/user-behavior")
    public AjaxResult getUserBehaviorAnalysis(
            @RequestParam(required = false) String timeRange,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        
        Map<String, Object> data = dashboardService.getUserBehaviorAnalysis(timeRange, startDate, endDate);
        return AjaxResult.success(data);
    }

    /**
     * 获取厨师绩效排行
     */
    @GetMapping("/chef-performance")
    public AjaxResult getChefPerformance(
            @RequestParam(required = false) String timeRange,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        
        Map<String, Object> data = dashboardService.getChefPerformance(timeRange, startDate, endDate);
        return AjaxResult.success(data);
    }
}

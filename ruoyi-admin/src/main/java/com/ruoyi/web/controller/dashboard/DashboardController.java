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

@RestController
@RequestMapping("/dashboard")
public class DashboardController extends BaseController {
    
    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/metrics")
    public AjaxResult getMetrics(
            @RequestParam(required = false) String timeRange,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        
        Map<String, Object> data = dashboardService.getDashboardMetrics(timeRange, startDate, endDate);
        return AjaxResult.success(data);
    }

    @GetMapping("/orderTrend")
    public AjaxResult getOrderTrend(
            @RequestParam(required = false) String timeRange,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        
        Map<String, Object> data = dashboardService.getOrderTrend(timeRange, startDate, endDate);
        return AjaxResult.success(data);
    }

    @GetMapping("/orderStatus")
    public AjaxResult getOrderStatus() {
        Map<String, Object> data = dashboardService.getOrderStatusDistribution();
        return AjaxResult.success(data);
    }

    @GetMapping("/popularDishes")
    public AjaxResult getPopularDishes(
            @RequestParam(required = false) String timeRange,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        
        Map<String, Object> data = dashboardService.getPopularDishes(timeRange, startDate, endDate);
        return AjaxResult.success(data);
    }

    @GetMapping("/chefWorkload")
    public AjaxResult getChefWorkload(
            @RequestParam(required = false) String timeRange,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        
        Map<String, Object> data = dashboardService.getChefWorkload(timeRange, startDate, endDate);
        return AjaxResult.success(data);
    }

    /**
     * 获取订单完成情况
     */
    @GetMapping("/orderCompletion")
    public AjaxResult getOrderCompletion(
            @RequestParam(required = false) String timeRange,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        
        Map<String, Object> data = dashboardService.getOrderCompletion(timeRange, startDate, endDate);
        return AjaxResult.success(data);
    }

    /**
     * 获取高峰时段分析
     */
    @GetMapping("/peakHours")
    public AjaxResult getPeakHours(
            @RequestParam(required = false) String timeRange,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        
        Map<String, Object> data = dashboardService.getPeakHours(timeRange, startDate, endDate);
        return AjaxResult.success(data);
    }
}

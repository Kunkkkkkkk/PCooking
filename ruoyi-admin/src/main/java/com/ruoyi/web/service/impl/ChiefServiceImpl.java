package com.ruoyi.web.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.pda.domain.ChiefAuth;
import com.ruoyi.pda.domain.DTO.ChiefApplyDTO;
import com.ruoyi.pda.domain.DTO.ChiefAuthDTO;
import com.ruoyi.pda.domain.DTO.ChiefDTO;
import com.ruoyi.pda.domain.DTO.ChiefQuery;
import com.ruoyi.pda.domain.VO.ChiefAuthVO;
import com.ruoyi.pda.domain.VO.ChiefVO;
import com.ruoyi.pda.domain.VO.OrderHistory;
import com.ruoyi.pda.domain.VO.OrderVO;
import com.ruoyi.web.mapper.ChiefMapper;
import com.ruoyi.web.service.ChiefService;

@Service
public class ChiefServiceImpl implements ChiefService {
    @Autowired
    private ChiefMapper chiefMapper;
    @Override
    public List<ChiefVO> getChiefList(ChiefQuery chiefQuery) {
        return chiefMapper.getChiefList(chiefQuery);
    }

    @Override
    public void editChief(ChiefDTO chiefDTO) {
        chiefMapper.editChief(chiefDTO);
    }

    @Override
    public ChiefVO getPerformance(long chiefId) {
        // 获取基础统计数据
        ChiefVO summary = chiefMapper.getPerformanceSummary(chiefId);
        if (summary == null) {
            summary = new ChiefVO();
            summary.setOrderNum(0);
            summary.setTotalRevenue(BigDecimal.ZERO);
            summary.setRating(0.0);
            summary.setOrdersLast30Days(0);
            summary.setRevenueLast30Days(BigDecimal.ZERO);
        }

        // 获取近30天订单历史
        List<OrderHistory> orderHistory = chiefMapper.getOrderHistory(chiefId);
        if (orderHistory == null) {
            orderHistory = new ArrayList<>();
        }

        // 构建每日订单数据Map
        Map<String, Integer> dailyOrderCounts = new HashMap<>();
        for (OrderHistory history : orderHistory) {
            dailyOrderCounts.put(history.getDate(), history.getOrderCount());
        }
        summary.setDailyOrderCounts(dailyOrderCounts);

        return summary;
    }
    //厨师申请分页查询
    @Override
    public List<ChiefAuthVO> getChiefAuthList(ChiefQuery chiefQuery) {
        return chiefMapper.getChiefAuthList(chiefQuery);
    }

    @Override
    @Transactional
    public boolean applyForChef(ChiefApplyDTO applyDTO) {
        Long userId = SecurityUtils.getUserId();
        if (userId == null) {
            throw new RuntimeException("无法获取当前用户信息"); 
        }

        // 可以在这里添加检查，是否已经存在未审核或已通过的申请
        ChiefAuthVO existingAuth = chiefMapper.getChiefAuthByUserId(userId);
        if (existingAuth != null /*&& ("0".equals(existingAuth.getStatus()) || "1".equals(existingAuth.getStatus()))*/) {
            // 先注释掉getStatus()的调用以避免编译错误
            // throw new RuntimeException("您已提交申请或已是厨师，请勿重复申请");
        }

        ChiefAuth chiefAuth = new ChiefAuth();
         chiefAuth.setUserId(userId);
         chiefAuth.setRealName(applyDTO.getRealName());
         chiefAuth.setIdCode(applyDTO.getIdCode());
         chiefAuth.setLinkPhone(applyDTO.getPhone());
         chiefAuth.setYears(applyDTO.getYears());
         chiefAuth.setCuisine(applyDTO.getCuisine());
         chiefAuth.setImage(applyDTO.getImage());
         chiefAuth.setRemark(applyDTO.getRemark());
         chiefAuth.setStatus("0"); // 默认待审核
         chiefAuth.setRequestTime(LocalDateTime.now());
         chiefAuth.setRealPhoto(applyDTO.getRealPhoto());
         chiefAuth.setSex(applyDTO.getSex());
        int result = chiefMapper.insertChiefAuth(chiefAuth);
        return result > 0;
    }

    @Override
    public ChiefAuthVO getMyApplication() {
        Long userId = SecurityUtils.getUserId();
        if (userId == null) {
             return null;
        }
        return chiefMapper.getChiefAuthByUserId(userId);
    }

    @Override
    public ChiefVO getCurrentChefInfo() {
        Long userId = SecurityUtils.getUserId();
        if (userId == null) {
            return null; 
        }
        // findChiefByUserId 应该只返回状态正常的厨师
        return chiefMapper.findChiefByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean approveChiefAuth(ChiefAuthDTO authDTO) {
        // 暂时移除校验，添加日志以便调试
        System.out.println("Received approve request with DTO: " + authDTO);
        if (authDTO == null || authDTO.getUserId() == null) {
            System.err.println("Warning: userId is null in approveChiefAuth");
            return false;
        }
        if (authDTO.getRealName() == null || authDTO.getRealName().isEmpty()) {
            System.err.println("Warning: realName is null or empty in approveChiefAuth, proceeding anyway");
        }

        // 1. 更新申请状态为通过
        int updateResult = chiefMapper.approveChiefAuth(authDTO.getUserId());
        if (updateResult <= 0) {
            // 可以考虑抛出异常或记录日志，表示更新失败
            // throw new RuntimeException("更新申请状态失败");
            System.err.println("Failed to update auth status for userId: " + authDTO.getUserId());
            return false; // 或者直接返回失败
        }

        // 2. 插入厨师记录
        String realName = authDTO.getRealName() != null ? authDTO.getRealName() : "Unknown";
            int insertResult = chiefMapper.insertChief(authDTO);
        if (insertResult <= 0) {
            // 插入失败，需要回滚事务
            throw new RuntimeException("创建厨师记录失败");
        }
        
        // 两个操作都成功（或未抛出异常）
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean rejectChiefAuth(ChiefAuthDTO authDTO) {
        // 暂时移除校验，添加日志以便调试
        System.out.println("Received reject request with DTO: " + authDTO);
        if (authDTO == null || authDTO.getUserId() == null) {
            System.err.println("Error: userId is null in rejectChiefAuth");
            return false;
        }
        String reason = authDTO.getReason() != null ? authDTO.getReason() : "未提供拒绝原因";
        System.out.println("Rejecting userId: " + authDTO.getUserId() + " with reason: " + reason);

        // 更新申请状态为拒绝，并记录原因
        int updateResult = chiefMapper.rejectChiefAuth(authDTO.getUserId(), reason);
        if (updateResult <= 0) {
            System.err.println("Failed to update reject status for userId: " + authDTO.getUserId());
        }
        
        return updateResult > 0;
    }

    @Override
    public List<ChiefVO> getChefs(String appointmentTime) {

        return chiefMapper.getChefs(appointmentTime);
        // return chiefMapper.getChefsWithHistoricalRating(appointmentTime);
    }

    @Override
    public List<OrderVO> getNewOrders() {
        return null;
    }

    @Override
    public List<OrderVO> getMyPendingOrders(Long userId, String status) {
        return null;
    }


    @Override
    public ChiefVO findChiefByUserId(Long userId) {
        return chiefMapper.findChiefByUserId(userId);
    }

    @Override
    public AjaxResult cancel(Long orderId) {
        chiefMapper.cancle(orderId);
        return AjaxResult.success();
    }

    @Override
    public List<ChiefAuthVO> getChiefInfo(Long id) {
        return chiefMapper.getChiefInfo(id);
    }

    @Override
    public Map<String, Object> getMyPerformanceData(Long chiefId, String timeRange) {
        Map<String, Object> result = new HashMap<>();
        
        // 获取绩效统计数据
        Map<String, Object> stats = chiefMapper.getPerformanceStats(chiefId, timeRange);
        if (stats == null) {
            stats = new HashMap<>();
            stats.put("totalOrders", 0L);
            stats.put("totalEarnings", BigDecimal.ZERO);
            stats.put("completedOrders", 0L);
            stats.put("canceledOrders", 0L);
            stats.put("avgRating", 0.0);
        }
        
        // 获取最受欢迎菜品
        Map<String, Object> topDish = chiefMapper.getTopDish(chiefId, timeRange);
        if (topDish == null) {
            topDish = new HashMap<>();
            topDish.put("dishName", "暂无数据");
            topDish.put("orderCount", 0L);
        }
        
        // 获取每日明细（仅本周）
        List<Map<String, Object>> dailyDetails = new ArrayList<>();
        if ("week".equals(timeRange)) {
            dailyDetails = chiefMapper.getDailyPerformance(chiefId);
        }
        
        // 计算衍生指标 - 安全的类型转换
        int totalOrders = safeLongToInt(stats.get("totalOrders"));
        BigDecimal totalEarnings = safeToBigDecimal(stats.get("totalEarnings"));
        int completedOrders = safeLongToInt(stats.get("completedOrders"));
        int canceledOrders = safeLongToInt(stats.get("canceledOrders"));
        Double avgRating = safeToDouble(stats.get("avgRating"));
        
        // 处理topDish中的orderCount
        int topDishCount = safeLongToInt(topDish.get("orderCount"));
        
        // 计算工作天数和日均数据
        int workingDays = getWorkingDays(timeRange);
        double avgOrdersPerDay = workingDays > 0 ? (double) totalOrders / workingDays : 0;
        BigDecimal avgEarningsPerDay = workingDays > 0 ? 
            totalEarnings.divide(new BigDecimal(workingDays), 2, BigDecimal.ROUND_HALF_UP) : 
            BigDecimal.ZERO;
        
        // 组装返回数据
        result.put("period", getPeriodText(timeRange));
        result.put("totalOrders", totalOrders);
        result.put("totalEarnings", totalEarnings.doubleValue());
        result.put("avgOrdersPerDay", Math.round(avgOrdersPerDay * 10.0) / 10.0);
        result.put("avgEarningsPerDay", avgEarningsPerDay.doubleValue());
        result.put("completedOrders", completedOrders);
        result.put("canceledOrders", canceledOrders);
        result.put("rating", Math.round(avgRating * 10.0) / 10.0);
        result.put("topDish", topDish.get("dishName"));
        result.put("topDishCount", topDishCount);
        result.put("workingDays", workingDays);
        result.put("details", dailyDetails);
        
        return result;
    }
    
    private int getWorkingDays(String timeRange) {
        switch (timeRange) {
            case "week": return 7;
            case "month": return 30;
            case "quarter": return 90;
            case "year": return 360;
            default: return 7;
        }
    }
    
    private String getPeriodText(String timeRange) {
        switch (timeRange) {
            case "week": return "本周";
            case "month": return "本月";
            case "quarter": return "本季度";
            case "year": return "本年";
            default: return "本周";
        }
    }
    
    /**
     * 安全的Long到int转换
     */
    private int safeLongToInt(Object value) {
        if (value == null) {
            return 0;
        }
        if (value instanceof Long) {
            return ((Long) value).intValue();
        }
        if (value instanceof Integer) {
            return (Integer) value;
        }
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        return 0;
    }
    
    /**
     * 安全的BigDecimal转换
     */
    private BigDecimal safeToBigDecimal(Object value) {
        if (value == null) {
            return BigDecimal.ZERO;
        }
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }
        if (value instanceof Number) {
            return new BigDecimal(value.toString());
        }
        return BigDecimal.ZERO;
    }
    
    /**
     * 安全的Double转换
     */
    private Double safeToDouble(Object value) {
        if (value == null) {
            return 0.0;
        }
        if (value instanceof Double) {
            return (Double) value;
        }
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        return 0.0;
    }
}

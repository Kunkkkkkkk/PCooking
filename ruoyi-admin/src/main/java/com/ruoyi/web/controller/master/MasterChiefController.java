package com.ruoyi.web.controller.master;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.RedisLockUtil;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.pda.domain.DTO.ChiefApplyDTO;
import com.ruoyi.pda.domain.DTO.ChiefAuthDTO;
import com.ruoyi.pda.domain.DTO.ChiefDTO;
import com.ruoyi.pda.domain.DTO.ChiefQuery;
import com.ruoyi.pda.domain.VO.ChiefAuthVO;
import com.ruoyi.pda.domain.VO.ChiefVO;
import com.ruoyi.pda.domain.VO.OrderVO;
import com.ruoyi.web.service.ChiefService;
import com.ruoyi.web.service.OrderService;

@RestController
@RequestMapping("/master") // 添加统一的路径前缀
public class MasterChiefController extends BaseController {
    @Autowired
    private ChiefService chiefService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RedisLockUtil redisLockUtil;

    private static final String ORDER_LOCK_PREFIX = "order:lock:";
    private static final long LOCK_TIMEOUT = 30L;

    //厨师查询
    @GetMapping("/chief/pageList")
    public TableDataInfo pageList(ChiefQuery chiefQuery) {
        startPage();
        List<ChiefVO> list = chiefService.getChiefList(chiefQuery);
        return getDataTable(list);
    }
    //编辑厨师信息
    @PostMapping("/chief/edit")
    public AjaxResult edit(@Validated @RequestBody ChiefDTO chiefDTO) {
        chiefService.editChief(chiefDTO);
        return AjaxResult.success();
    }
    //绩效查询：1.总订单数（status为2的已完成的）  2.总钱数（status为2的已完成的） 3.星级（status为2且有评价的，评价的打分的平均值）4.近30天订单数（status为2且finish_time在30天内的）和近30天的营业额（status为2且create_time在30天内的）
    @GetMapping("/chief/getPerformanceData")
    public AjaxResult getPerformanceData(@RequestParam long chiefId) {
        ChiefVO chiefVO = chiefService.getPerformance(chiefId);
        return AjaxResult.success(chiefVO);
    }

    @GetMapping("/chiefAuth/list")
    public TableDataInfo chiefAuthList(ChiefQuery chiefQuery) {
        startPage();
        List<ChiefAuthVO> list = chiefService.getChiefAuthList(chiefQuery);
        return getDataTable(list);
    }

    /**
     * 用户提交厨师认证申请
     */
    @PostMapping("/chief/apply")
    public AjaxResult applyForChef(@Validated @RequestBody ChiefApplyDTO applyDTO) {
        try {
            boolean result = chiefService.applyForChef(applyDTO);
            return result ? AjaxResult.success("申请提交成功，请等待审核") : AjaxResult.error("申请提交失败");
        } catch (RuntimeException e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 获取当前用户的厨师申请信息
     */
    @GetMapping("/chief/myApplication")
    public AjaxResult getMyApplication() {
        ChiefAuthVO application = chiefService.getMyApplication();
        return AjaxResult.success(application);
    }

    /**
     * 获取当前登录用户的厨师信息（如果用户是厨师）
     */
    @GetMapping("/chief/info")
    public AjaxResult getCurrentChefInfo() {
        ChiefVO chefInfo = chiefService.getCurrentChefInfo();
        return AjaxResult.success(chefInfo);
    }

    /**
     * 审核通过厨师认证申请
     */
    @PostMapping("/chiefAuth/approve")
    public AjaxResult approveChiefAuth(@Validated @RequestBody ChiefAuthDTO authDTO) {
        try {
            boolean result = chiefService.approveChiefAuth(authDTO);
            return result ? AjaxResult.success("审核通过成功") : AjaxResult.error("审核操作失败");
        } catch (IllegalArgumentException e) {
             return AjaxResult.error("审核失败：" + e.getMessage());
        } catch (RuntimeException e) {
            // Log the exception e
            return AjaxResult.error("审核操作失败，请联系管理员");
        }
    }

    /**
     * 拒绝厨师认证申请
     */
    @PostMapping("/chiefAuth/reject")
    public AjaxResult rejectChiefAuth(@Validated @RequestBody ChiefAuthDTO authDTO) {
         try {
            boolean result = chiefService.rejectChiefAuth(authDTO);
            return result ? AjaxResult.success("拒绝成功") : AjaxResult.error("拒绝操作失败");
        } catch (IllegalArgumentException e) {
             return AjaxResult.error("拒绝失败：" + e.getMessage());
        } catch (RuntimeException e) {
            // Log the exception e
            return AjaxResult.error("拒绝操作失败，请联系管理员");
        }
    }

    /**
     * 获取预约时间里可用的厨师信息
     */
    @GetMapping("/chief/listNotBusy")
    public AjaxResult getChefs(@RequestParam(value = "appointmentTime", required = true) String appointmentTime) {
        try {
            List<ChiefVO> chefs = chiefService.getChefs(appointmentTime);
            return AjaxResult.success(chefs);
        } catch (Exception e) {
            return AjaxResult.error("获取厨师列表失败: " + e.getMessage());
        }
    }

    /**
     * 获取新订单列表
     */
    @GetMapping("/chief/orders/new")
    public AjaxResult getNewOrders() {
        List<OrderVO> orders = chiefService.getNewOrders();
        return AjaxResult.success(orders);
    }
    /**
     * 获取我的订单列表
     */
    @GetMapping("/chief/orders/my")
    public AjaxResult getMyPendingOrders(@RequestParam String status) {
        Long userId = SecurityUtils.getUserId();
        List<OrderVO> orders = chiefService.getMyPendingOrders(userId, status);
        return AjaxResult.success(orders);
    }
    
    /**
     * 获取订单列表
     */
    @GetMapping("/chief/orders/list")
    public TableDataInfo getOrderList(@RequestParam(value = "status", required = false) String status, 
                                      @RequestParam(value = "type", required = false) String type) {
        startPage();
        LoginUser loginUser = getLoginUser();
        List<OrderVO> list = new ArrayList<>();
        
        try {
            // type=new 查询新订单（待接单状态）
            if ("new".equals(type)) {
                list = orderService.getOrderList(status, null);
            } 
            // type=my 查询我的订单（根据状态）
            else if ("my".equals(type)) {
                Long userId = loginUser.getUserId();
                ChiefVO chief = chiefService.findChiefByUserId(userId);
                if (chief != null) {
                    list = orderService.getOrderList(status, chief.getId());
                }
            }
            return getDataTable(list);
        } catch (Exception e) {
            e.printStackTrace();
            return getDataTable(list);
        }
    }

    @PostMapping("chief/orders/accept/{orderId}")
    public AjaxResult accept(@PathVariable Long orderId) {
        long userId = SecurityUtils.getUserId();
        
        // 检查是否是自己的订单
        long orderUserId = orderService.getUserIdByOrderId(orderId);
        if (orderUserId == userId) {
            return AjaxResult.error("无法接下自己的订单");
        }

        // 获取厨师信息
        ChiefVO chief = chiefService.findChiefByUserId(userId);
        if (chief == null) {
            return AjaxResult.error("未找到厨师信息");
        }

        // 构建锁键和锁值
        String lockKey = ORDER_LOCK_PREFIX + orderId;
        String lockValue = chief.getId() + ":" + System.currentTimeMillis();

        try {
            // 尝试获取分布式锁
            boolean locked = redisLockUtil.tryLock(lockKey, lockValue, LOCK_TIMEOUT, TimeUnit.SECONDS);
            if (!locked) {
                return AjaxResult.error("订单正在被其他厨师处理，请稍后再试");
            }

            // 二次检查订单状态
            if (!orderService.isOrderAvailable(orderId)) {
                return AjaxResult.error("订单已被其他厨师接单");
            }

            // 执行接单操作
            orderService.accept(orderId, chief.getId());
            return AjaxResult.success("接单成功");

        } catch (Exception e) {
            return AjaxResult.error("接单失败：" + e.getMessage());
        } finally {
            // 释放分布式锁
            redisLockUtil.releaseLock(lockKey, lockValue);
        }
    }
    //取消订单（待烹饪）
    @PostMapping("/chief/orders/cancel/{orderId}")
    public AjaxResult cancel(@PathVariable Long orderId) {
        return chiefService.cancel(orderId);
    }
    @PostMapping("chief/cook/{orderId}")
    public AjaxResult cookComplete(@PathVariable Long orderId) {
        AjaxResult ajax = AjaxResult.success();
        orderService.cookComplete(orderId);
        return ajax;
    }

    @PostMapping("chief/takeout/{orderId}")
    public AjaxResult takeout(@PathVariable Long orderId) {
        AjaxResult ajax = AjaxResult.success();
        orderService.takeout(orderId);
        return ajax;
    }

}
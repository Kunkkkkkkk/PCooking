package com.ruoyi.web.controller.master;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.pda.domain.Comment;
import com.ruoyi.pda.domain.DTO.CreateOrderDTO;
import com.ruoyi.pda.domain.DTO.OrderCommentDTO;
import com.ruoyi.pda.domain.DTO.OrderQuery;
import com.ruoyi.pda.domain.VO.OrderVO;
import com.ruoyi.web.controller.tool.PicgoUpload;
import com.ruoyi.web.service.OrderService;

@RestController
public class MasterOrderController extends BaseController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/master/order/pageList")
    public TableDataInfo pageList(OrderQuery orderQuery) {
        startPage();
        long userId = SecurityUtils.getUserId();
        orderQuery.setUserId(userId);
        List<OrderVO> list = orderService.getList(orderQuery);
        return getDataTable(list);
    }
    //后台查看订单
    @GetMapping("/master/order/pageList2")
    public TableDataInfo pageList2(OrderQuery orderQuery) {
        startPage();
        List<OrderVO> list = orderService.getList2(orderQuery);
        return getDataTable(list);
    }
    //获取订单详情
    @GetMapping("/master/order/detail")
    public AjaxResult detail(@RequestParam("orderId") long orderId) {
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data", orderService.getDetail(orderId));
        return ajax;
    }
    //查看订单的用户评价
    @GetMapping("/master/order/review")
    public AjaxResult review(@RequestParam("orderId") long orderId) {
        AjaxResult ajax = AjaxResult.success();
        Comment comment = orderService.getReview(orderId);
        if(comment != null&&comment.getImgUrls()!=null) {
            comment.setImgUrlsList(Arrays.asList(comment.getImgUrls().split(",")));
        }
        ajax.put("data", comment);
        return ajax;
    }
    //已完成订单写评价
    @PostMapping("/master/order/review")
    public AjaxResult review(@RequestBody OrderCommentDTO orderCommentDTO) throws Exception {
        AjaxResult ajax = AjaxResult.success();
        //上传图片
        String images = PicgoUpload.uploadByPicgo(orderCommentDTO.getImageUrlsList());
        orderCommentDTO.setImagesUrls(images);
        orderService.insertComment(orderCommentDTO);
    return ajax;}

    // 创建订单
    @PostMapping("/master/order/create")
    public AjaxResult createOrder(@RequestBody CreateOrderDTO createOrderDTO) {
        // 获取当前登录用户ID
        Long userId = SecurityUtils.getUserId();
        
        // 设置订单基本信息
        createOrderDTO.setUserId(userId);
        createOrderDTO.setStatus("-1"); // 设置为待支付状态
        createOrderDTO.setCreateTime(LocalDateTime.now());
        
        // 调用服务创建订单
        long orderId = orderService.createOrder(createOrderDTO);
        
        AjaxResult ajax = AjaxResult.success("订单创建成功");
        ajax.put("orderId", orderId);
        return ajax;
    }
    
    // 支付订单
    @PostMapping("/master/order/pay")
    public AjaxResult payOrder(@RequestBody Map<String, Object> params) {
        long orderId = Long.parseLong(params.get("orderId").toString());
        String payMethod = params.get("payMethod").toString();
        
        boolean result = orderService.payOrder(orderId, payMethod);
        
        if (result) {
            return AjaxResult.success("支付成功");
        } else {
            return AjaxResult.error("支付失败，请重试");
        }
    }
    
    // 取消订单
    @PostMapping("/master/order/cancel")
    public AjaxResult cancelOrder(@RequestBody Map<String, Object> params) {
        long orderId = Long.parseLong(params.get("orderId").toString());
        
        boolean result = orderService.cancelOrder(orderId);
        
        if (result) {
            return AjaxResult.success("订单已取消");
        } else {
            return AjaxResult.error("取消订单失败，请重试");
        }
    }

    @GetMapping("/master/order/current")
    public AjaxResult getCurrentOrder() {
        AjaxResult ajax = AjaxResult.success();
        OrderVO order = orderService.getCurrentOrder();
        ajax.put("data", order);
        return ajax;
    }
    


}

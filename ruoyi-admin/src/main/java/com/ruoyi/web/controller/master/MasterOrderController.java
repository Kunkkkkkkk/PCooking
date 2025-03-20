package com.ruoyi.web.controller.master;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.pda.domain.Comment;
import com.ruoyi.pda.domain.DTO.OrderQuery;
import com.ruoyi.pda.domain.VO.OrderVO;
import com.ruoyi.web.service.OrderService;

@RestController
public class MasterOrderController extends BaseController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/master/order/pageList")
    public TableDataInfo pageList(OrderQuery orderQuery) {
        startPage();
        List<OrderVO> list = orderService.getList(orderQuery);
        return getDataTable(list);
    }
    //获取订单详情
    @GetMapping("/master/order/detail")
    public AjaxResult detail(@RequestParam("orderId") long orderId) {
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data", orderService.getDetail(orderId));
        return ajax;
    }
    //查看订单绑定的用户信息
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

}

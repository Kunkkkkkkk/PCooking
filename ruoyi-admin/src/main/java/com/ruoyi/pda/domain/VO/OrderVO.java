package com.ruoyi.pda.domain.VO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.ruoyi.pda.domain.DTO.OrderItem;
import lombok.Data;

/**
 * 订单视图对象
 */
@Data
public class OrderVO {
    private Long orderId;
    private Long chiefId;
    private Long userId;
    private Double price;
    private String status;
    private Date createTime;
    private Date orderTime;
    private Long commentId;
    private String chiefName;
    private String userName;
    private String remark;
    private String userAvatar; 
    // 厨师真实姓名
    private String chiefRealName;
    private Date cookedTime;
    // 厨师头像
    private String chiefAvatar;
    private List<OrderItem> orderItems;



}

package com.ruoyi.pda.domain;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 厨师认证实体对象 master_chief_auth
 */
@Data
public class ChiefAuth {
    private static final long serialVersionUID = 1L;

    /** 认证ID */
    private Long authId;

    /** 真实姓名 */
    private String realName;

    /** 身份证号 */
    private String idCode;

    /** 申请时间 */
    private LocalDateTime requestTime;

    /** 资格证图片 */
    private String image; // 对应数据库 identify_image

    /** 状态（0待审核 1已通过 2已拒绝） */
    private String status;

    /** 申请用户ID */
    private Long userId;

    /** 从业年限 */
    private Integer years;

    /** 擅长菜系，逗号分隔 */
    private String cuisine;

    /** 自我介绍 */
    private String remark;

    /** 拒绝原因 */
    private String reply;

    /** 联系方式 */
    private String linkPhone; // 对应数据库 link_phone
} 
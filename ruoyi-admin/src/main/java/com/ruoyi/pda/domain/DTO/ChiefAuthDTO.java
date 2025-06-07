package com.ruoyi.pda.domain.DTO;

import lombok.Data;

@Data
public class ChiefAuthDTO {
    private Long userId;
    private String realName;
    private String reason; // 用于拒绝审核
    private String remark;
    private String phone;
    private String email;
    private String description;
    private String realPhoto;
}

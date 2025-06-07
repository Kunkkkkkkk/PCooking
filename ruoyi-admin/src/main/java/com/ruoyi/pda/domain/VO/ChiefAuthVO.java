package com.ruoyi.pda.domain.VO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChiefAuthVO {
    private long authId;
    private String realName;
    private String idCode;
    private LocalDateTime requestTime;
    private String image;
    private long userId;
    private String status;
    private int years;
    //擅长菜系
    private String cuisine;
    private String remark;
    private String reply;
    private String phone;
    private String sex;
    private String realPhoto;
}

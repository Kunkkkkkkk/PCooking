package com.ruoyi.pda.domain.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChiefDTO {
    private long chiefId;
    private long userId;
    private String status;
    private long role;
    private String realName;
    private LocalDateTime joinTime;
    //真实照片（展示用）
    private String realPhoto;
    //介绍信息
    private String description;
    //0无 1银牌厨师  2金牌厨师
    private String isRecommend;
}

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
}

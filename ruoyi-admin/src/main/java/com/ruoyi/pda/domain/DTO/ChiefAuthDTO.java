package com.ruoyi.pda.domain.DTO;

public class ChiefAuthDTO {
    private Long userId;
    private String realName;
    private String reason; // 用于拒绝审核

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

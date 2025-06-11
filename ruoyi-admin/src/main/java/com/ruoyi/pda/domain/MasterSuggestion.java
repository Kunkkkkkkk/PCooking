package com.ruoyi.pda.domain;

import java.util.Date;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户建议对象 master_suggestion
 * 
 * @author ruoyi
 * @date 2025-01-11
 */
public class MasterSuggestion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 建议ID */
    private Long suggestId;

    /** 建议内容 */
    @Excel(name = "建议内容")
    private String content;

    /** 建议类型（1:菜品增加 2:服务反馈） */
    @Excel(name = "建议类型", readConverterExp = "1=菜品增加,2=服务反馈")
    private String type;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public void setSuggestId(Long suggestId) 
    {
        this.suggestId = suggestId;
    }

    public Long getSuggestId() 
    {
        return suggestId;
    }
    
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public Date getCreateTime() 
    {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) 
    {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append(getClass().getName()).append(" [")
            .append("Hash = ").append(hashCode())
            .append(", suggestId=").append(suggestId)
            .append(", content=").append(content)
            .append(", type=").append(type)
            .append(", userId=").append(userId)
            .append(", createTime=").append(createTime)
            .append(", serialVersionUID=").append(serialVersionUID)
            .append("]").toString();
    }
} 
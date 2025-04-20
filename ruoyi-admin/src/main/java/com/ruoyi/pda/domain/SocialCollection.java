package com.ruoyi.pda.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SysUser;

/**
 * 社交内容收藏对象 master_social_collection
 */
public class SocialCollection extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 收藏ID */
    private Long collectionId;

    /** 社交内容ID */
    @Excel(name = "社交内容ID")
    private Long socialId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 关联的用户信息 */
    private SysUser user;
    
    /** 关联的社交内容 */
    private Social social;

    public void setCollectionId(Long collectionId) 
    {
        this.collectionId = collectionId;
    }

    public Long getCollectionId() 
    {
        return collectionId;
    }

    public void setSocialId(Long socialId) 
    {
        this.socialId = socialId;
    }

    public Long getSocialId() 
    {
        return socialId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public SysUser getUser() 
    {
        return user;
    }

    public void setUser(SysUser user) 
    {
        this.user = user;
    }
    
    public Social getSocial() 
    {
        return social;
    }

    public void setSocial(Social social) 
    {
        this.social = social;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("collectionId", getCollectionId())
            .append("socialId", getSocialId())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .append("user", getUser())
            .append("social", getSocial())
            .toString();
    }
} 
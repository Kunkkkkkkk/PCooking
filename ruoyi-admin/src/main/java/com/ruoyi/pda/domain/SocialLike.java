package com.ruoyi.pda.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SysUser;

/**
 * 社交点赞对象 master_social_like
 */
public class SocialLike extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 点赞ID */
    private Long likeId;

    /** 社交内容ID */
    @Excel(name = "社交内容ID")
    private Long socialId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 关联的用户信息 */
    private SysUser user;

    public Long getLikeId()
    {
        return likeId;
    }

    public void setLikeId(Long likeId)
    {
        this.likeId = likeId;
    }

    public Long getSocialId()
    {
        return socialId;
    }

    public void setSocialId(Long socialId)
    {
        this.socialId = socialId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
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

    public SysUser getUser()
    {
        return user;
    }

    public void setUser(SysUser user)
    {
        this.user = user;
    }
} 
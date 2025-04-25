package com.ruoyi.pda.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SysUser;

/**
 * 社交评论对象 master_social_comment
 */
public class SocialComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评论ID */
    private Long commentId;

    /** 社交内容ID */
    @Excel(name = "社交内容ID")
    private Long socialId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String content;

    /** 父评论ID */
    @Excel(name = "父评论ID")
    private Long parentId;

    /** 状态（0正常 1删除） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=删除")
    private String status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 关联的用户信息 */
    private SysUser user;
    
    /** 回复列表 */
    private List<SocialComment> replies;

    /** 时间显示 */
    private String timeAgo;

    /** 关联的社交内容 */
    private Social social;

    private String queryContent;

    public String getQueryContent() {
        return queryContent;
    }

    public void setQueryContent(String queryContent) {
        this.queryContent = queryContent;
    }

    public Long getCommentId()
    {
        return commentId;
    }

    public void setCommentId(Long commentId)
    {
        this.commentId = commentId;
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

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
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

    public List<SocialComment> getReplies()
    {
        return replies;
    }

    public void setReplies(List<SocialComment> replies)
    {
        this.replies = replies;
    }

    public String getTimeAgo()
    {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo)
    {
        this.timeAgo = timeAgo;
    }

    public Social getSocial()
    {
        return social;
    }

    public void setSocial(Social social)
    {
        this.social = social;
    }
} 
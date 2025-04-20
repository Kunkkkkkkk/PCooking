package com.ruoyi.pda.domain;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SysUser;

/**
 * 社交内容对象 master_social
 * 
 */
public class Social extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 社交内容ID */
    private Long socialId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 社交内容 */
    @Excel(name = "社交内容")
    private String content;

    /** 图片URL */
    @Excel(name = "图片URL")
    private String imageUrl;

    /** 图片高度 */
    @Excel(name = "图片高度")
    private Integer imageHeight;

    /** 标签，逗号分隔 */
    @Excel(name = "标签")
    private String tags;

    /** 状态（0正常 1删除） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=删除")
    private String status;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 关联的用户信息 */
    private SysUser user;
    
    /** 点赞数 - 计算得出 */
    private Integer likeCount;
    
    /** 评论数 - 计算得出 */
    private Integer commentCount;
    
    /** 当前用户是否点赞 */
    private Boolean liked;
    
    /** 当前用户是否收藏 */
    private Boolean collected;
    
    /** 社交内容标签列表 */
    private List<String> tagList;
    
    /** 评论列表 */
    private List<SocialComment> comments;

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

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setImageUrl(String imageUrl) 
    {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() 
    {
        return imageUrl;
    }

    public void setImageHeight(Integer imageHeight) 
    {
        this.imageHeight = imageHeight;
    }

    public Integer getImageHeight() 
    {
        return imageHeight;
    }
    
    public void setTags(String tags) 
    {
        this.tags = tags;
    }

    public String getTags() 
    {
        return tags;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setUpdateTime(Date updateTime) 
    {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() 
    {
        return updateTime;
    }

    public SysUser getUser() 
    {
        return user;
    }

    public void setUser(SysUser user) 
    {
        this.user = user;
    }
    
    public Integer getLikeCount() 
    {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) 
    {
        this.likeCount = likeCount;
    }

    public Integer getCommentCount() 
    {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) 
    {
        this.commentCount = commentCount;
    }
    
    public Boolean getLiked() 
    {
        return liked;
    }

    public void setLiked(Boolean liked) 
    {
        this.liked = liked;
    }
    
    public Boolean getCollected() 
    {
        return collected;
    }

    public void setCollected(Boolean collected) 
    {
        this.collected = collected;
    }
    
    public List<String> getTagList() 
    {
        return tagList;
    }

    public void setTagList(List<String> tagList) 
    {
        this.tagList = tagList;
    }
    
    public List<SocialComment> getComments() 
    {
        return comments;
    }

    public void setComments(List<SocialComment> comments) 
    {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("socialId", getSocialId())
            .append("userId", getUserId())
            .append("content", getContent())
            .append("imageUrl", getImageUrl())
            .append("imageHeight", getImageHeight())
            .append("tags", getTags())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("user", getUser())
            .append("likeCount", getLikeCount())
            .append("commentCount", getCommentCount())
            .append("liked", getLiked())
            .append("collected", getCollected())
            .toString();
    }
} 
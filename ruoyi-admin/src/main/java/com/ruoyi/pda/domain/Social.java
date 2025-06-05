package com.ruoyi.pda.domain;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SysUser;

import lombok.Data;

/**
 * 社交内容对象 master_social
 * 
 */
@Data
public class Social extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    //最新：1 最热：0
    private String type;
    /** 社交内容ID */
    private Long socialId;
    //置顶
    private String isTop;
    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    private String userName;
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

    private String title;
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
    
    /** 收藏时间（用于收藏夹页面显示） */
    private Date collectionTime;
    
    /** 社交内容标签列表 */
    private List<String> tagList;

    /** 评论列表 */
    private List<SocialComment> comments;

    private Date startDate;
    private Date endDate;
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
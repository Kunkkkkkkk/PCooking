package com.ruoyi.pda.service;

import java.util.List;

import com.ruoyi.pda.domain.SocialComment;

/**
 * 社交评论 服务层
 * 
 * @author ruoyi
 */
public interface ISocialCommentService
{
    /**
     * 查询社交评论列表
     * 
     * @param socialComment 社交评论信息
     * @return 社交评论集合
     */
    public List<SocialComment> selectSocialCommentList(SocialComment socialComment);

    /**
     * 根据社交内容ID查询评论列表
     * 
     * @param socialId 社交内容ID
     * @return 社交评论集合
     */
    public List<SocialComment> selectSocialCommentBySocialId(Long socialId);

    /**
     * 查询评论回复列表
     * 
     * @param parentId 父评论ID
     * @return 社交评论集合
     */
    public List<SocialComment> selectSocialCommentReplies(Long parentId);

    /**
     * 根据评论ID查询社交评论
     * 
     * @param commentId 评论ID
     * @return 社交评论
     */
    public SocialComment selectSocialCommentById(Long commentId);

    /**
     * 新增社交评论
     * 
     * @param socialComment 社交评论信息
     * @return 结果
     */
    public int insertSocialComment(SocialComment socialComment);

    /**
     * 修改社交评论
     * 
     * @param socialComment 社交评论信息
     * @return 结果
     */
    public int updateSocialComment(SocialComment socialComment);

    /**
     * 删除社交评论信息
     * 
     * @param commentId 评论ID
     * @return 结果
     */
    public int deleteSocialCommentById(Long commentId);

    /**
     * 批量删除社交评论信息
     * 
     * @param commentIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSocialCommentByIds(String[] commentIds);

    /**
     * 统计社交内容评论数量
     * 
     * @param socialId 社交内容ID
     * @return 评论数量
     */
    public int countSocialComment(Long socialId);
} 
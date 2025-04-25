package com.ruoyi.pda.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.pda.domain.SocialComment;
import com.ruoyi.pda.mapper.SocialCommentMapper;
import com.ruoyi.pda.service.ISocialCommentService;

/**
 * 社交评论服务实现
 * 
 * @author ruoyi
 */
@Service
public class SocialCommentServiceImpl implements ISocialCommentService
{
    @Autowired
    private SocialCommentMapper socialCommentMapper;

    /**
     * 查询社交评论列表
     * 
     * @param socialComment 社交评论信息
     * @return 社交评论集合
     */
    @Override
    public List<SocialComment> selectSocialCommentList(SocialComment socialComment)
    {
        return socialCommentMapper.selectSocialCommentList(socialComment);
    }

    /**
     * 查询指定社交内容的评论列表
     * 
     * @param socialId 社交内容ID
     * @return 社交评论集合
     */
    @Override
    public List<SocialComment> selectSocialCommentBySocialId(Long socialId)
    {
        return socialCommentMapper.selectSocialCommentBySocialId(socialId);
    }

    /**
     * 查询评论的回复列表
     * 
     * @param parentId 父评论ID
     * @return 回复评论集合
     */
    @Override
    public List<SocialComment> selectSocialCommentReplies(Long parentId)
    {
        return socialCommentMapper.selectSocialCommentReplies(parentId);
    }

    /**
     * 根据评论ID查询评论信息
     * 
     * @param commentId 评论ID
     * @return 评论信息
     */
    @Override
    public SocialComment selectSocialCommentById(Long commentId)
    {
        return socialCommentMapper.selectSocialCommentById(commentId);
    }

    /**
     * 新增社交评论
     * 
     * @param socialComment 社交评论信息
     * @return 结果
     */
    @Override
    public int insertSocialComment(SocialComment socialComment)
    {
        socialComment.setStatus("0");
        return socialCommentMapper.insertSocialComment(socialComment);
    }

    /**
     * 修改社交评论
     * 
     * @param socialComment 社交评论信息
     * @return 结果
     */
    @Override
    public int updateSocialComment(SocialComment socialComment)
    {
        return socialCommentMapper.updateSocialComment(socialComment);
    }

    /**
     * 删除社交评论
     * 
     * @param commentId 评论ID
     * @return 结果
     */
    @Override
    public int deleteSocialCommentById(Long commentId)
    {
        return socialCommentMapper.deleteSocialCommentById(commentId);
    }

    /**
     * 批量删除社交评论
     *
     * @param commentIds 需要删除的评论ID数组
     * @return 结果
     */
    @Override
    public int deleteSocialCommentByIds(String[] commentIds)
    {
//        return socialCommentMapper.deleteSocialCommentByIds(Convert.toStrArray(commentIds));
        return 1;
    }

    /**
     * 获取社交内容的评论数量
     * 
     * @param socialId 社交内容ID
     * @return 评论数量
     */
    @Override
    public int countSocialComment(Long socialId)
    {
        return socialCommentMapper.countSocialComment(socialId);
    }

    @Override
    public List<SocialComment> SocialCommentList(String contentQuery) {
        return socialCommentMapper.SocialCommentLists(contentQuery);
    }
}
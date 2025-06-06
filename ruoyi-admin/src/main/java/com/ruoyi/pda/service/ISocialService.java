package com.ruoyi.pda.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.pda.domain.Social;
import com.ruoyi.pda.domain.SocialCollection;
import com.ruoyi.pda.domain.SocialComment;
import com.ruoyi.pda.domain.SocialLike;

/**
 * 社交内容 服务层
 */
public interface ISocialService
{
    /**
     * 查询社交内容列表
     * 
     * @param social 社交内容信息
     * @return 社交内容集合
     */
    public List<Social> selectSocialList(Social social);


    public List<Social> selectSocialList2(Social social);
    /**
     * 查询社交内容信息
     * 
     * @param socialId 社交内容ID
     * @return 社交内容信息
     */
    public Social selectSocialById(Long socialId);

    /**
     * 新增社交内容
     * 
     * @param social 社交内容信息
     * @return 结果
     */
    public int insertSocial(Social social);

    /**
     * 修改社交内容
     * 
     * @param social 社交内容信息
     * @return 结果
     */
    public AjaxResult updateSocial(Social social);

    /**
     * 删除社交内容信息
     * 
     * @param socialId 社交内容ID
     * @return 结果
     */
    public int deleteSocialById(Long socialId);

    /**
     * 批量删除社交内容信息
     * 
     * @param socialIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSocialByIds(String[] socialIds);

    /**
     * 批量删除社交内容信息
     * 
     * @param socialIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSocialByIds(Long[] socialIds);

    /**
     * 点赞社交内容
     * 
     * @param socialId 社交内容ID
     * @param userId 用户ID
     * @return 结果
     */
    public int likeSocial(Long socialId, Long userId);
    
    /**
     * 取消点赞社交内容
     * 
     * @param socialId 社交内容ID
     * @param userId 用户ID
     * @return 结果
     */
    public int unlikeSocial(Long socialId, Long userId);
    
    /**
     * 检查用户是否已点赞社交内容
     * 
     * @param socialId 社交内容ID
     * @param userId 用户ID
     * @return 是否已点赞
     */
    public boolean checkSocialLiked(Long socialId, Long userId);
    
    /**
     * 查询社交内容点赞列表
     * 
     * @param socialLike 社交点赞信息
     * @return 社交点赞集合
     */
    public List<SocialLike> selectSocialLikeList(SocialLike socialLike);
    
    /**
     * 查询社交内容评论列表
     * 
     * @param socialComment 社交评论信息
     * @return 社交评论集合
     */
    public List<SocialComment> selectSocialCommentList(SocialComment socialComment);
    
    /**
     * 查询社交内容评论信息
     * 
     * @param commentId 评论ID
     * @return 社交评论信息
     */
    public SocialComment selectSocialCommentById(Long commentId);
    
    /**
     * 查询社交内容评论回复列表
     * 
     * @param parentId 父评论ID
     * @return 评论回复集合
     */
    public List<SocialComment> selectSocialCommentReplies(Long parentId);
    
    /**
     * 新增社交内容评论
     * 
     * @param socialComment 社交评论信息
     * @return 结果
     */
    public int insertSocialComment(SocialComment socialComment);
    
    /**
     * 修改社交内容评论
     * 
     * @param socialComment 社交评论信息
     * @return 结果
     */
    public int updateSocialComment(SocialComment socialComment);
    
    /**
     * 删除社交内容评论
     * 
     * @param commentId 评论ID
     * @return 结果
     */
    public int deleteSocialCommentById(Long commentId);
    
    /**
     * 批量删除社交内容评论
     * 
     * @param commentIds 需要删除的评论ID数组
     * @return 结果
     */
    public int deleteSocialCommentByIds(String[] commentIds);

    /**
     * 收藏社交内容
     * 
     * @param socialId 社交内容ID
     * @param userId 用户ID
     * @return 结果
     */
    public int collectSocial(Long socialId, Long userId);
    
    /**
     * 取消收藏社交内容
     * 
     * @param socialId 社交内容ID
     * @param userId 用户ID
     * @return 结果
     */
    public int uncollectSocial(Long socialId, Long userId);
    
    /**
     * 检查用户是否已收藏社交内容
     * 
     * @param socialId 社交内容ID
     * @param userId 用户ID
     * @return 是否已收藏
     */
    public boolean checkSocialCollected(Long socialId, Long userId);
    
    /**
     * 查询社交内容收藏列表
     * 
     * @param socialCollection 社交收藏信息
     * @return 社交收藏集合
     */
    public List<SocialCollection> selectSocialCollectionList(SocialCollection socialCollection);
    
    /**
     * 查询用户收藏的社交内容
     * 
     * @param userId 用户ID
     * @return 社交内容集合
     */
    public List<Social> selectUserCollections(Long userId);

    /**
     * 查询用户点赞的社交内容
     * 
     * @param userId 用户ID
     * @return 社交内容集合
     */
    public List<Social> selectUserLikes(Long userId);

    /**
     * 查询用户发表的评论列表
     * 
     * @param userId 用户ID
     * @return 评论集合
     */
    public List<SocialComment> selectUserComments(Long userId);

    int deleteSocialComment(Long commentId);

    AjaxResult changeStatus(Social social);

    // ================ 收藏夹相关方法 ================
    
    /**
     * 收藏到指定收藏夹
     */
    public int collectSocialToFolder(Long socialId, Long userId, String folderName);

    /**
     * 取消收藏（删除所有收藏记录）
     */
    public int uncollectSocialFromFolder(Long socialId, Long userId, String folderName);

    /**
     * 获取用户收藏夹列表（带统计）
     */
    public List<Map<String, Object>> getUserCollectionFolders(Long userId);

    /**
     * 获取指定收藏夹的内容
     */
    public List<Social> getFolderContent(Long userId, String folderName, Integer pageNum, Integer pageSize);

    /**
     * 检查收藏状态
     */
    public Map<String, Object> checkCollectionStatus(Long socialId, Long userId);
} 
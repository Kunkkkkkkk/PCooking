package com.ruoyi.pda.service;

import java.util.List;

import com.ruoyi.pda.domain.SocialLike;

/**
 * 社交点赞 服务层
 * 
 * @author ruoyi
 */
public interface ISocialLikeService
{
    /**
     * 查询社交点赞列表
     * 
     * @param socialLike 社交点赞信息
     * @return 社交点赞集合
     */
    public List<SocialLike> selectSocialLikeList(SocialLike socialLike);

    /**
     * 根据社交内容ID查询点赞列表
     * 
     * @param socialId 社交内容ID
     * @return 社交点赞集合
     */
    public List<SocialLike> selectSocialLikeBySocialId(Long socialId);

    /**
     * 查询用户是否点赞特定社交内容
     * 
     * @param socialId 社交内容ID
     * @param userId 用户ID
     * @return 社交点赞信息
     */
    public SocialLike selectSocialLikeByUserAndSocial(Long socialId, Long userId);

    /**
     * 新增社交点赞
     * 
     * @param socialLike 社交点赞信息
     * @return 结果
     */
    public int insertSocialLike(SocialLike socialLike);

    /**
     * 删除社交点赞信息
     * 
     * @param likeId 点赞ID
     * @return 结果
     */
    public int deleteSocialLikeById(Long likeId);

    /**
     * 批量删除社交点赞信息
     * 
     * @param likeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSocialLikeByIds(String[] likeIds);

    /**
     * 删除用户对特定社交内容的点赞
     * 
     * @param socialId 社交内容ID
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteSocialLikeByUserAndSocial(Long socialId, Long userId);

    /**
     * 统计社交内容点赞数量
     * 
     * @param socialId 社交内容ID
     * @return 点赞数量
     */
    public int countSocialLike(Long socialId);

    boolean checkSocialLiked(Long socialId, Long userId);

    int likeSocial(Long socialId, Long userId);

    int unlikeSocial(Long socialId, Long userId);
} 
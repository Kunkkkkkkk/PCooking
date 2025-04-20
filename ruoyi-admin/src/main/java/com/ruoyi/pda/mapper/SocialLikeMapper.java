package com.ruoyi.pda.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ruoyi.pda.domain.SocialLike;

/**
 * 社交点赞 数据层
 */
@Mapper
public interface SocialLikeMapper
{
    /**
     * 查询社交点赞列表
     * 
     * @param socialLike 社交点赞信息
     * @return 社交点赞集合
     */
    public List<SocialLike> selectSocialLikeList(SocialLike socialLike);

    /**
     * 查询社交内容的点赞
     * 
     * @param socialId 社交内容ID
     * @return 社交点赞集合
     */
    public List<SocialLike> selectSocialLikeBySocialId(Long socialId);

    /**
     * 查询用户是否点赞
     * 
     * @param socialId 社交内容ID
     * @param userId 用户ID
     * @return 社交点赞信息
     */
    public SocialLike selectSocialLikeByUserAndSocial(@Param("socialId") Long socialId, @Param("userId") Long userId);

    /**
     * 新增社交点赞
     * 
     * @param socialLike 社交点赞信息
     * @return 结果
     */
    public int insertSocialLike(SocialLike socialLike);

    /**
     * 删除社交点赞
     * 
     * @param likeId 社交点赞ID
     * @return 结果
     */
    public int deleteSocialLikeById(Long likeId);

    /**
     * 批量删除社交点赞
     * 
     * @param likeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSocialLikeByIds(String[] likeIds);

    /**
     * 删除用户点赞
     * 
     * @param socialId 社交内容ID
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteSocialLikeByUserAndSocial(@Param("socialId") Long socialId, @Param("userId") Long userId);

    /**
     * 获取社交内容点赞数量
     * 
     * @param socialId 社交内容ID
     * @return 点赞数量
     */
    public int countSocialLike(Long socialId);
} 
package com.ruoyi.pda.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.pda.domain.SocialCollection;

/**
 * 社交收藏 数据层
 */
public interface SocialCollectionMapper
{
    /**
     * 查询社交收藏列表
     * 
     * @param socialCollection 社交收藏信息
     * @return 社交收藏集合
     */
    public List<SocialCollection> selectSocialCollectionList(SocialCollection socialCollection);

    /**
     * 查询社交内容的收藏
     * 
     * @param socialId 社交内容ID
     * @return 社交收藏集合
     */
    public List<SocialCollection> selectSocialCollectionBySocialId(Long socialId);

    /**
     * 查询用户是否收藏
     * 
     * @param socialId 社交内容ID
     * @param userId 用户ID
     * @return 社交收藏信息
     */
    public SocialCollection selectSocialCollectionByUserAndSocial(@Param("socialId") Long socialId, @Param("userId") Long userId);

    /**
     * 新增社交收藏
     * 
     * @param socialCollection 社交收藏信息
     * @return 结果
     */
    public int insertSocialCollection(SocialCollection socialCollection);

    /**
     * 删除社交收藏
     * 
     * @param collectionId 社交收藏ID
     * @return 结果
     */
    public int deleteSocialCollectionById(Long collectionId);

    /**
     * 批量删除社交收藏
     * 
     * @param collectionIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSocialCollectionByIds(String[] collectionIds);

    /**
     * 删除用户收藏
     * 
     * @param socialId 社交内容ID
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteSocialCollectionByUserAndSocial(@Param("socialId") Long socialId, @Param("userId") Long userId);

    /**
     * 获取社交内容收藏数量
     * 
     * @param socialId 社交内容ID
     * @return 收藏数量
     */
    public int countSocialCollection(Long socialId);
} 
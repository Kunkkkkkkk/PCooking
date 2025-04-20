package com.ruoyi.pda.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.pda.domain.SocialLike;
import com.ruoyi.pda.mapper.SocialLikeMapper;
import com.ruoyi.pda.service.ISocialLikeService;

/**
 * 社交点赞 服务层实现
 * 
 * @author ruoyi
 */
@Service
public class SocialLikeServiceImpl implements ISocialLikeService 
{
    @Autowired
    private SocialLikeMapper socialLikeMapper;

    /**
     * 查询社交点赞列表
     * 
     * @param socialLike 社交点赞信息
     * @return 社交点赞
     */
    @Override
    public List<SocialLike> selectSocialLikeList(SocialLike socialLike)
    {
        return socialLikeMapper.selectSocialLikeList(socialLike);
    }
    
    /**
     * 根据社交内容ID查询点赞列表
     * 
     * @param socialId 社交内容ID
     * @return 社交点赞集合
     */
    @Override
    public List<SocialLike> selectSocialLikeBySocialId(Long socialId)
    {
        return socialLikeMapper.selectSocialLikeBySocialId(socialId);
    }
    
    /**
     * 查询用户是否点赞过指定社交内容
     * 
     * @param socialId 社交内容ID
     * @param userId 用户ID
     * @return 社交点赞信息
     */
    @Override
    public SocialLike selectSocialLikeByUserAndSocial(Long socialId, Long userId)
    {
        return socialLikeMapper.selectSocialLikeByUserAndSocial(socialId, userId);
    }

    /**
     * 新增社交点赞
     * 
     * @param socialLike 社交点赞信息
     * @return 结果
     */
    @Override
    public int insertSocialLike(SocialLike socialLike)
    {
        return socialLikeMapper.insertSocialLike(socialLike);
    }

    /**
     * 删除社交点赞对象
     * 
     * @param likeId 社交点赞ID
     * @return 结果
     */
    @Override
    public int deleteSocialLikeById(Long likeId)
    {
        return socialLikeMapper.deleteSocialLikeById(likeId);
    }

    /**
     * 批量删除社交点赞
     * 
     * @param likeIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSocialLikeByIds(String[] likeIds)
    {
        return socialLikeMapper.deleteSocialLikeByIds(Convert.toStrArray(Arrays.toString(likeIds)));
    }
    
    /**
     * 删除用户对社交内容的点赞
     * 
     * @param socialId 社交内容ID
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int deleteSocialLikeByUserAndSocial(Long socialId, Long userId)
    {
        return socialLikeMapper.deleteSocialLikeByUserAndSocial(socialId, userId);
    }
    
    /**
     * 统计社交内容点赞数量
     * 
     * @param socialId 社交内容ID
     * @return 点赞数量
     */
    @Override
    public int countSocialLike(Long socialId)
    {
        return socialLikeMapper.countSocialLike(socialId);
    }

    @Override
    public boolean checkSocialLiked(Long socialId, Long userId) {
        return false;
    }

    @Override
    public int likeSocial(Long socialId, Long userId) {
        return 0;
    }

    @Override
    public int unlikeSocial(Long socialId, Long userId) {
        return 0;
    }
}
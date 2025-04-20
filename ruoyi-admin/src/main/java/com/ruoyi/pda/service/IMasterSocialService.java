package com.ruoyi.pda.service;

import java.util.List;

import com.ruoyi.pda.domain.MasterSocial;

/**
 * 社交内容Service接口
 * 
 * @author ruoyi
 */
public interface IMasterSocialService 
{
    /**
     * 查询社交内容
     * 
     * @param socialId 社交内容ID
     * @return 社交内容
     */
    public MasterSocial selectMasterSocialBySocialId(Long socialId);

    /**
     * 查询社交内容列表
     * 
     * @param masterSocial 社交内容
     * @return 社交内容集合
     */
    public List<MasterSocial> selectMasterSocialList(MasterSocial masterSocial);

    /**
     * 新增社交内容
     * 
     * @param masterSocial 社交内容
     * @return 结果
     */
    public int insertMasterSocial(MasterSocial masterSocial);

    /**
     * 修改社交内容
     * 
     * @param masterSocial 社交内容
     * @return 结果
     */
    public int updateMasterSocial(MasterSocial masterSocial);

    /**
     * 批量删除社交内容
     * 
     * @param socialIds 需要删除的社交内容ID
     * @return 结果
     */
    public int deleteMasterSocialBySocialIds(Long[] socialIds);

    /**
     * 删除社交内容信息
     * 
     * @param socialId 社交内容ID
     * @return 结果
     */
    public int deleteMasterSocialBySocialId(Long socialId);
    
    /**
     * 前端展示社交内容列表（包含点赞和评论数）
     * 
     * @param userId 当前用户ID
     * @return 社交内容集合
     */
    public List<MasterSocial> selectFrontSocialList(Long userId);
    
    /**
     * 前端查看社交内容详情（包含用户是否点赞和收藏）
     * 
     * @param socialId 社交内容ID
     * @param userId 当前用户ID
     * @return 社交内容
     */
    public MasterSocial selectFrontSocialDetail(Long socialId, Long userId);
    
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
} 
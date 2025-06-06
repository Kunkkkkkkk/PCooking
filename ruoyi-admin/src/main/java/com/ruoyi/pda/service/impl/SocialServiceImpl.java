package com.ruoyi.pda.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.pda.domain.Social;
import com.ruoyi.pda.domain.SocialCollection;
import com.ruoyi.pda.domain.SocialComment;
import com.ruoyi.pda.domain.SocialLike;
import com.ruoyi.pda.mapper.SocialCollectionMapper;
import com.ruoyi.pda.mapper.SocialCommentMapper;
import com.ruoyi.pda.mapper.SocialLikeMapper;
import com.ruoyi.pda.mapper.SocialMapper;
import com.ruoyi.pda.service.ISocialService;

/**
 * 社交内容 服务层实现
 */
@Service
public class SocialServiceImpl implements ISocialService
{
    @Autowired
    private SocialMapper socialMapper;
    
    @Autowired
    private SocialLikeMapper socialLikeMapper;
    
    @Autowired
    private SocialCommentMapper socialCommentMapper;
    
    @Autowired
    private SocialCollectionMapper socialCollectionMapper;

    /**
     * 查询社交内容列表
     * 
     * @param social 社交内容信息
     * @return 社交内容
     */
    @Override
    public List<Social> selectSocialList(Social social)
    {
        return socialMapper.selectSocialList(social);
    }

    /**
     * 查询社交内容列表
     *
     * @param social 社交内容信息
     * @return 社交内容
     */
    @Override
    public List<Social> selectSocialList2(Social social)
    {
        return socialMapper.selectSocialListWithStatus(social);
    }


    /**
     * 查询社交内容信息
     * 
     * @param socialId 社交内容ID
     * @return 社交内容
     */
    @Override
    public Social selectSocialById(Long socialId)
    {
        Social social = socialMapper.selectSocialById(socialId);
        
        // 设置当前登录用户的点赞状态
        if (social != null && SecurityUtils.getAuthentication() != null) {
            try {
                Long userId = SecurityUtils.getUserId();
                social.setLiked(checkSocialLiked(socialId, userId));
                // 设置收藏状态
                social.setCollected(checkSocialCollected(socialId, userId));
            } catch (Exception e) {
                // 获取用户ID失败，可能是未登录状态
                social.setLiked(false);
                social.setCollected(false);
            }
        }
        
        return social;
    }

    /**
     * 新增社交内容
     * 
     * @param social 社交内容信息
     * @return 结果
     */
    @Override
    public int insertSocial(Social social)
    {
        social.setCreateTime(DateUtils.getNowDate());
        // 如果没有设置状态，默认为正常
        if (social.getStatus() == null)
        {
            social.setStatus("0");
        }
        // 如果没有设置点赞数和评论数，默认为0
        if (social.getLikeCount() == null)
        {
            social.setLikeCount(0);
        }
        if (social.getCommentCount() == null)
        {
            social.setCommentCount(0);
        }
        return socialMapper.insertSocial(social);
    }

    /**
     * 修改社交内容
     * 
     * @param social 社交内容信息
     * @return 结果
     */
    @Override
    public AjaxResult updateSocial(Social social)
    {
        if(socialMapper.checkUpdate(social)>0){
            socialMapper.updateSocial(social);
            return AjaxResult.success();
        }else {
            return AjaxResult.error("数据已被他人修改，刷新后重试");
        }
    }

    /**
     * 删除社交内容对象
     * 
     * @param socialId 社交内容ID
     * @return 结果
     */
    @Override
    public int deleteSocialById(Long socialId)
    {
        return socialMapper.deleteSocialById(socialId);
    }

    /**
     * 批量删除社交内容对象
     * 
     * @param socialIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSocialByIds(String[] socialIds)
    {
        return socialMapper.deleteSocialByIds(socialIds);
    }
    
    /**
     * 批量删除社交内容对象
     * 
     * @param socialIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSocialByIds(Long[] socialIds)
    {
        // 转换为String数组
        String[] strIds = new String[socialIds.length];
        for (int i = 0; i < socialIds.length; i++)
        {
            strIds[i] = String.valueOf(socialIds[i]);
        }
        return socialMapper.deleteSocialByIds(strIds);
    }

    /**
     * 点赞社交内容
     * 
     * @param socialId 社交内容ID
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int likeSocial(Long socialId, Long userId)
    {
        // 检查是否已经点赞
        if (checkSocialLiked(socialId, userId))
        {
            return 0;
        }
        
        // 创建点赞记录
        SocialLike socialLike = new SocialLike();
        socialLike.setSocialId(socialId);
        socialLike.setUserId(userId);
        socialLike.setCreateTime(DateUtils.getNowDate());
        
        return socialLikeMapper.insertSocialLike(socialLike);
    }
    
    /**
     * 取消点赞社交内容
     * 
     * @param socialId 社交内容ID
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int unlikeSocial(Long socialId, Long userId)
    {
        return socialLikeMapper.deleteSocialLikeByUserAndSocial(socialId, userId);
    }
    
    /**
     * 检查用户是否已点赞社交内容
     * 
     * @param socialId 社交内容ID
     * @param userId 用户ID
     * @return 是否已点赞
     */
    @Override
    public boolean checkSocialLiked(Long socialId, Long userId)
    {
        return socialLikeMapper.selectSocialLikeByUserAndSocial(socialId, userId) != null;
    }
    
    /**
     * 查询社交内容点赞列表
     * 
     * @param socialLike 社交点赞信息
     * @return 社交点赞集合
     */
    @Override
    public List<SocialLike> selectSocialLikeList(SocialLike socialLike)
    {
        return socialLikeMapper.selectSocialLikeList(socialLike);
    }
    
    /**
     * 查询社交内容评论列表
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
     * 查询社交内容评论信息
     * 
     * @param commentId 评论ID
     * @return 社交评论信息
     */
    @Override
    public SocialComment selectSocialCommentById(Long commentId)
    {
        return socialCommentMapper.selectSocialCommentById(commentId);
    }
    
    /**
     * 查询社交内容评论回复列表
     * 
     * @param parentId 父评论ID
     * @return 评论回复集合
     */
    @Override
    public List<SocialComment> selectSocialCommentReplies(Long parentId)
    {
        return socialCommentMapper.selectSocialCommentReplies(parentId);
    }
    
    /**
     * 新增社交内容评论
     * 
     * @param socialComment 社交评论信息
     * @return 结果
     */
    @Override
    public int insertSocialComment(SocialComment socialComment)
    {
        socialComment.setCreateTime(DateUtils.getNowDate());
        // 如果没有设置状态，默认为正常
        if (socialComment.getStatus() == null)
        {
            socialComment.setStatus("0");
        }
        return socialCommentMapper.insertSocialComment(socialComment);
    }
    
    /**
     * 修改社交内容评论
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
     * 删除社交内容评论
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
     * 批量删除社交内容评论
     * 
     * @param commentIds 需要删除的评论ID数组
     * @return 结果
     */
    @Override
    public int deleteSocialCommentByIds(String[] commentIds)
    {
        return socialCommentMapper.deleteSocialCommentByIds(commentIds);
    }

    /**
     * 收藏社交内容
     * 
     * @param socialId 社交内容ID
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int collectSocial(Long socialId, Long userId)
    {
        // 检查是否已经收藏
        if (checkSocialCollected(socialId, userId))
        {
            return 0;
        }
        
        // 创建收藏记录
        SocialCollection socialCollection = new SocialCollection();
        socialCollection.setSocialId(socialId);
        socialCollection.setUserId(userId);
        socialCollection.setCreateTime(DateUtils.getNowDate());
        
        return socialCollectionMapper.insertSocialCollection(socialCollection);
    }
    
    /**
     * 取消收藏社交内容（删除该用户对该帖子的所有收藏记录）
     * 
     * @param socialId 社交内容ID
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int uncollectSocial(Long socialId, Long userId)
    {
        return socialCollectionMapper.deleteSocialCollectionByUserAndSocial(socialId, userId);
    }
    
    /**
     * 检查用户是否已收藏社交内容
     * 
     * @param socialId 社交内容ID
     * @param userId 用户ID
     * @return 是否已收藏
     */
    @Override
    public boolean checkSocialCollected(Long socialId, Long userId)
    {
        return socialCollectionMapper.selectSocialCollectionByUserAndSocial(socialId, userId) != null;
    }
    
    /**
     * 查询社交内容收藏列表
     * 
     * @param socialCollection 社交收藏信息
     * @return 社交收藏集合
     */
    @Override
    public List<SocialCollection> selectSocialCollectionList(SocialCollection socialCollection)
    {
        return socialCollectionMapper.selectSocialCollectionList(socialCollection);
    }
    
    /**
     * 查询用户收藏的社交内容
     * 
     * @param userId 用户ID
     * @return 社交内容集合
     */
    @Override
    public List<Social> selectUserCollections(Long userId)
    {
        SocialCollection query = new SocialCollection();
        query.setUserId(userId);
        List<SocialCollection> collections = socialCollectionMapper.selectSocialCollectionList(query);
        
        return collections.stream()
                .map(SocialCollection::getSocial)
                .collect(Collectors.toList());
    }

    /**
     * 查询用户点赞的社交内容
     * 
     * @param userId 用户ID
     * @return 社交内容集合
     */
    @Override
    public List<Social> selectUserLikes(Long userId)
    {
        SocialLike query = new SocialLike();
        query.setUserId(userId);
        List<SocialLike> likes = socialLikeMapper.selectSocialLikeList(query);
        
        // 获取点赞的社交内容ID列表
        List<Long> socialIds = likes.stream()
                .map(SocialLike::getSocialId)
                .collect(Collectors.toList());
        
        // 如果没有点赞记录，返回空列表
        if (socialIds.isEmpty()) {
            return new ArrayList<>();
        }
        
        // 批量获取社交内容详情
        List<Social> result = new ArrayList<>();
        for (Long socialId : socialIds) {
            Social social = selectSocialById(socialId);
            if (social != null && "0".equals(social.getStatus())) {
                result.add(social);
            }
        }
        
        return result;
    }

    /**
     * 查询用户发表的评论列表
     * 
     * @param userId 用户ID
     * @return 评论集合
     */
    @Override
    public List<SocialComment> selectUserComments(Long userId)
    {
        SocialComment query = new SocialComment();
        query.setUserId(userId);
        List<SocialComment> comments = socialCommentMapper.selectSocialCommentList(query);
        
        // 对于每个评论，获取其关联的社交内容信息
        for (SocialComment comment : comments) {
            if (comment.getSocialId() != null) {
                Social social = socialMapper.selectSocialById(comment.getSocialId());
                comment.setSocial(social);
            }
        }
        
        return comments;
    }

    /**
     * 删除评论及其所有回复
     * 
     * @param commentId 评论ID
     * @return 结果
     */
    @Override
    public int deleteSocialComment(Long commentId) {

        // 使用新的方法一次性删除评论及其所有回复
        int rows = socialCommentMapper.deleteCommentAndReplies(commentId);
        

        return rows;
    }

    @Override
    public AjaxResult changeStatus(Social social) {
        if(socialMapper.checkUpdate(social)>0){
            socialMapper.changeStatus(social);
            return AjaxResult.success();
        }else {
            return AjaxResult.error("数据已被他人修改，刷新后重试");
        }
    }

    // ================ 收藏夹功能实现 ================

    /**
     * 收藏到指定收藏夹
     */
    @Override
    public int collectSocialToFolder(Long socialId, Long userId, String folderName) {
        // 检查是否已经收藏过这个帖子（任何收藏夹）
        if (checkSocialCollected(socialId, userId)) {
            return 0; // 已经收藏过了
        }
        
        // 创建收藏记录
        SocialCollection socialCollection = new SocialCollection();
        socialCollection.setSocialId(socialId);
        socialCollection.setUserId(userId);
        // 确保folderName正确设置
        String targetFolderName = (folderName != null && !folderName.trim().isEmpty()) ? folderName.trim() : "默认";
        socialCollection.setFolderName(targetFolderName);
        socialCollection.setCreateTime(DateUtils.getNowDate());
        
        System.out.println("收藏到收藏夹: " + targetFolderName); // 调试信息
        
        return socialCollectionMapper.insertSocialCollection(socialCollection);
    }

    /**
     * 取消收藏（简单删除，不管收藏夹）
     */
    @Override
    public int uncollectSocialFromFolder(Long socialId, Long userId, String folderName) {
        // 直接删除用户对该帖子的所有收藏记录
        return socialCollectionMapper.deleteSocialCollectionByUserAndSocial(socialId, userId);
    }

    /**
     * 获取用户收藏夹列表（带统计）
     */
    @Override
    public List<Map<String, Object>> getUserCollectionFolders(Long userId) {
        // 使用现有的查询方法，然后手动分组统计
        SocialCollection query = new SocialCollection();
        query.setUserId(userId);
        List<SocialCollection> collections = socialCollectionMapper.selectSocialCollectionList(query);
        
        Map<String, Long> folderStats = collections.stream()
                .collect(Collectors.groupingBy(
                    collection -> collection.getFolderName() != null ? collection.getFolderName() : "默认",
                    Collectors.counting()
                ));
        
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map.Entry<String, Long> entry : folderStats.entrySet()) {
            Map<String, Object> folder = new HashMap<>();
            folder.put("folderName", entry.getKey());
            folder.put("count", entry.getValue());
            result.add(folder);
        }
        
        // 如果没有收藏夹，至少返回默认收藏夹
        if (result.isEmpty()) {
            Map<String, Object> defaultFolder = new HashMap<>();
            defaultFolder.put("folderName", "默认");
            defaultFolder.put("count", 0);
            result.add(defaultFolder);
        }
        
        return result;
    }

    /**
     * 获取指定收藏夹的内容
     */
    @Override
    public List<Social> getFolderContent(Long userId, String folderName, Integer pageNum, Integer pageSize) {
        // 使用现有的查询方法，然后过滤收藏夹
        SocialCollection query = new SocialCollection();
        query.setUserId(userId);
        List<SocialCollection> allCollections = socialCollectionMapper.selectSocialCollectionList(query);
        
        // 过滤指定收藏夹的内容
        List<SocialCollection> folderCollections = allCollections.stream()
                .filter(collection -> {
                    String collectionFolderName = collection.getFolderName() != null ? collection.getFolderName() : "默认";
                    return collectionFolderName.equals(folderName);
                })
                .collect(Collectors.toList());
        
        // 简单分页（实际项目中应该在数据库层分页）
        int start = (pageNum - 1) * pageSize;
        int end = Math.min(start + pageSize, folderCollections.size());
        
        if (start >= folderCollections.size()) {
            return new ArrayList<>();
        }
        
        List<SocialCollection> paginatedCollections = folderCollections.subList(start, end);
        
        return paginatedCollections.stream()
                .map(collection -> {
                    Social social = selectSocialById(collection.getSocialId());
                    if (social != null) {
                        // 添加收藏时间信息
                        social.setCollectionTime(collection.getCreateTime());
                    }
                    return social;
                })
                .filter(social -> social != null && "0".equals(social.getStatus()))
                .collect(Collectors.toList());
    }

    /**
     * 检查收藏状态
     */
    @Override
    public Map<String, Object> checkCollectionStatus(Long socialId, Long userId) {
        Map<String, Object> result = new HashMap<>();
        
        // 简单检查是否收藏
        boolean collected = checkSocialCollected(socialId, userId);
        result.put("collected", collected);
        
        if (collected) {
            // 找到收藏夹信息
            SocialCollection query = new SocialCollection();
            query.setUserId(userId);
            query.setSocialId(socialId);
            List<SocialCollection> collections = socialCollectionMapper.selectSocialCollectionList(query);
            
            if (!collections.isEmpty()) {
                String folderName = collections.get(0).getFolderName() != null ? collections.get(0).getFolderName() : "默认";
                result.put("folderName", folderName);
            }
        }
        
        return result;
    }
}
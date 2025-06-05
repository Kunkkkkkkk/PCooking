package com.ruoyi.pda.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ruoyi.pda.domain.Social;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 社交内容 数据层
 */
@Mapper
public interface SocialMapper
{
    /**
     * 查询社交内容列表
     * 
     * @param social 社交内容信息
     * @return 社交内容集合
     */
    public List<Social> selectSocialList(Social social);



    public List<Social> selectSocialListWithStatus(Social social);
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
    public int updateSocial(Social social);

    /**
     * 删除社交内容
     * 
     * @param socialId 社交内容ID
     * @return 结果
     */
    public int deleteSocialById(Long socialId);

    /**
     * 批量删除社交内容
     * 
     * @param socialIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSocialByIds(String[] socialIds);

    /**
     * 检查社交内容是否被用户点赞
     * 
     * @param socialId 社交内容ID
     * @param userId 用户ID
     * @return 结果
     */
    public int checkSocialLikedByUser(@Param("socialId") Long socialId, @Param("userId") Long userId);

    /**
     * 点赞社交内容
     * 
     * @param socialId 社交内容ID
     * @return 结果
     */
    public int likeSocial(@Param("socialId") Long socialId);

    /**
     * 更新评论数量
     * 
     * @param socialId 社交内容ID
     * @return 结果
     */
    public int updateCommentCount(@Param("socialId") Long socialId);
    @Select("select count(1) from master_social where update_time =#{updateTime} and social_id=#{socialId}")
    public int checkUpdate(Social social);
    @Update("update master_social set status=#{status},update_time=Now() where social_id=#{socialId} ")
    void changeStatus(Social social);
}
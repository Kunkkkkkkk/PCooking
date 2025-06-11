package com.ruoyi.pda.service;

import java.util.List;

import com.ruoyi.pda.domain.MasterSuggestion;

/**
 * 用户建议Service接口
 * 
 * @author ruoyi
 * @date 2025-01-11
 */
public interface IMasterSuggestionService 
{
    /**
     * 查询用户建议
     * 
     * @param suggestId 用户建议主键
     * @return 用户建议
     */
    public MasterSuggestion selectMasterSuggestionBySuggestId(Long suggestId);

    /**
     * 查询用户建议列表
     * 
     * @param masterSuggestion 用户建议
     * @return 用户建议集合
     */
    public List<MasterSuggestion> selectMasterSuggestionList(MasterSuggestion masterSuggestion);

    /**
     * 新增用户建议
     * 
     * @param masterSuggestion 用户建议
     * @return 结果
     */
    public int insertMasterSuggestion(MasterSuggestion masterSuggestion);

    /**
     * 修改用户建议
     * 
     * @param masterSuggestion 用户建议
     * @return 结果
     */
    public int updateMasterSuggestion(MasterSuggestion masterSuggestion);

    /**
     * 批量删除用户建议
     * 
     * @param suggestIds 需要删除的用户建议主键集合
     * @return 结果
     */
    public int deleteMasterSuggestionBySuggestIds(Long[] suggestIds);

    /**
     * 删除用户建议信息
     * 
     * @param suggestId 用户建议主键
     * @return 结果
     */
    public int deleteMasterSuggestionBySuggestId(Long suggestId);
} 
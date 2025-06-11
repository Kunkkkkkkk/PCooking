package com.ruoyi.pda.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.pda.domain.MasterSuggestion;
import com.ruoyi.pda.mapper.MasterSuggestionMapper;
import com.ruoyi.pda.service.IMasterSuggestionService;

/**
 * 用户建议Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-01-11
 */
@Service
public class MasterSuggestionServiceImpl implements IMasterSuggestionService 
{
    @Autowired
    private MasterSuggestionMapper masterSuggestionMapper;

    /**
     * 查询用户建议
     * 
     * @param suggestId 用户建议主键
     * @return 用户建议
     */
    @Override
    public MasterSuggestion selectMasterSuggestionBySuggestId(Long suggestId)
    {
        return masterSuggestionMapper.selectMasterSuggestionBySuggestId(suggestId);
    }

    /**
     * 查询用户建议列表
     * 
     * @param masterSuggestion 用户建议
     * @return 用户建议
     */
    @Override
    public List<MasterSuggestion> selectMasterSuggestionList(MasterSuggestion masterSuggestion)
    {
        return masterSuggestionMapper.selectMasterSuggestionList(masterSuggestion);
    }

    /**
     * 新增用户建议
     * 
     * @param masterSuggestion 用户建议
     * @return 结果
     */
    @Override
    public int insertMasterSuggestion(MasterSuggestion masterSuggestion)
    {
        masterSuggestion.setCreateTime(new Date());
        return masterSuggestionMapper.insertMasterSuggestion(masterSuggestion);
    }

    /**
     * 修改用户建议
     * 
     * @param masterSuggestion 用户建议
     * @return 结果
     */
    @Override
    public int updateMasterSuggestion(MasterSuggestion masterSuggestion)
    {
        return masterSuggestionMapper.updateMasterSuggestion(masterSuggestion);
    }

    /**
     * 批量删除用户建议
     * 
     * @param suggestIds 需要删除的用户建议主键
     * @return 结果
     */
    @Override
    public int deleteMasterSuggestionBySuggestIds(Long[] suggestIds)
    {
        return masterSuggestionMapper.deleteMasterSuggestionBySuggestIds(suggestIds);
    }

    /**
     * 删除用户建议信息
     * 
     * @param suggestId 用户建议主键
     * @return 结果
     */
    @Override
    public int deleteMasterSuggestionBySuggestId(Long suggestId)
    {
        return masterSuggestionMapper.deleteMasterSuggestionBySuggestId(suggestId);
    }
} 
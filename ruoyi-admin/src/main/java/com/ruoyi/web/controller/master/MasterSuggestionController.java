package com.ruoyi.web.controller.master;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.pda.domain.MasterSuggestion;
import com.ruoyi.pda.service.IMasterSuggestionService;

/**
 * 用户建议Controller
 * 
 * @author ruoyi
 * @date 2025-01-11
 */
@RestController
@RequestMapping("/master/suggestion")
public class MasterSuggestionController extends BaseController
{
    @Autowired
    private IMasterSuggestionService masterSuggestionService;

    /**
     * 查询用户建议列表
     */
    @GetMapping("/list")
    public TableDataInfo list(MasterSuggestion masterSuggestion)
    {
        startPage();
        List<MasterSuggestion> list = masterSuggestionService.selectMasterSuggestionList(masterSuggestion);
        return getDataTable(list);
    }

    /**
     * 获取用户建议详细信息
     */
    @GetMapping(value = "/{suggestId}")
    public AjaxResult getInfo(@PathVariable("suggestId") Long suggestId)
    {
        return success(masterSuggestionService.selectMasterSuggestionBySuggestId(suggestId));
    }

    /**
     * 新增用户建议
     */
    @Log(title = "用户建议", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MasterSuggestion masterSuggestion)
    {
        // 设置当前用户ID
        masterSuggestion.setUserId(SecurityUtils.getUserId());
        masterSuggestion.setCreateTime(new Date());
        return toAjax(masterSuggestionService.insertMasterSuggestion(masterSuggestion));
    }

    /**
     * 修改用户建议
     */
    @Log(title = "用户建议", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MasterSuggestion masterSuggestion)
    {
        return toAjax(masterSuggestionService.updateMasterSuggestion(masterSuggestion));
    }

    /**
     * 删除用户建议
     */
    @Log(title = "用户建议", businessType = BusinessType.DELETE)
	@DeleteMapping("/{suggestIds}")
    public AjaxResult remove(@PathVariable Long[] suggestIds)
    {
        return toAjax(masterSuggestionService.deleteMasterSuggestionBySuggestIds(suggestIds));
    }
} 
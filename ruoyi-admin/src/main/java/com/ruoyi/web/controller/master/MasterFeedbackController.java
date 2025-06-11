package com.ruoyi.web.controller.master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.pda.domain.MasterSuggestion;
import com.ruoyi.pda.service.IMasterSuggestionService;

/**
 * 反馈管理Controller
 * 
 * @author ruoyi
 * @date 2025-01-11
 */
@RestController
@RequestMapping("/master/feedback")
public class MasterFeedbackController extends BaseController
{
    @Autowired
    private IMasterSuggestionService masterSuggestionService;

    /**
     * 查询反馈列表
     */
    @GetMapping("/list")
    public TableDataInfo list(MasterSuggestion masterSuggestion)
    {
        startPage();
        List<MasterSuggestion> list = masterSuggestionService.selectMasterSuggestionList(masterSuggestion);
        return getDataTable(list);
    }

    /**
     * 获取反馈详细信息
     */
    @GetMapping(value = "/{suggestId}")
    public AjaxResult getInfo(@PathVariable("suggestId") Long suggestId)
    {
        return success(masterSuggestionService.selectMasterSuggestionBySuggestId(suggestId));
    }
} 
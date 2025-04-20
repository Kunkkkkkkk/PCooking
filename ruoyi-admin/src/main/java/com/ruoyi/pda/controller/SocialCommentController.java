package com.ruoyi.pda.controller;

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
import com.ruoyi.pda.domain.SocialComment;
import com.ruoyi.pda.service.ISocialCommentService;

/**
 * 社交评论 控制层
 */
@RestController
@RequestMapping("/pda/comment")
public class SocialCommentController extends BaseController
{
    @Autowired
    private ISocialCommentService socialCommentService;

    /**
     * 查询评论列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SocialComment socialComment)
    {
        startPage();
        List<SocialComment> list = socialCommentService.selectSocialCommentList(socialComment);
        return getDataTable(list);
    }

    /**
     * 根据社交内容ID查询评论列表
     */
    @GetMapping("/bySocial/{socialId}")
    public AjaxResult listBySocialId(@PathVariable("socialId") Long socialId)
    {
        List<SocialComment> comments = socialCommentService.selectSocialCommentBySocialId(socialId);
        return success(comments);
    }

    /**
     * 获取评论详细信息
     */
    @GetMapping("/{commentId}")
    public AjaxResult getInfo(@PathVariable("commentId") Long commentId)
    {
        return success(socialCommentService.selectSocialCommentById(commentId));
    }

    /**
     * 获取评论回复列表
     */
    @GetMapping("/replies/{parentId}")
    public AjaxResult getReplies(@PathVariable("parentId") Long parentId)
    {
        List<SocialComment> replies = socialCommentService.selectSocialCommentReplies(parentId);
        return success(replies);
    }

    /**
     * 新增评论
     */
    @Log(title = "社交评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SocialComment socialComment)
    {
        // 设置当前登录用户ID
        socialComment.setUserId(SecurityUtils.getUserId());
        return toAjax(socialCommentService.insertSocialComment(socialComment));
    }

    /**
     * 修改评论
     */
    @Log(title = "社交评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SocialComment socialComment)
    {
        return toAjax(socialCommentService.updateSocialComment(socialComment));
    }

//    /**
//     * 删除评论
//     */
//    @Log(title = "社交评论", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{commentIds}")
//    public AjaxResult remove(@PathVariable Long[] commentIds)
//    {
//        return toAjax(socialCommentService.deleteSocialCommentByIds(commentIds));
//    }
}
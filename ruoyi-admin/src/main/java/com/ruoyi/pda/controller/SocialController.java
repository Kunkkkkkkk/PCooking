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
import com.ruoyi.pda.domain.Social;
import com.ruoyi.pda.domain.SocialComment;
import com.ruoyi.pda.domain.SocialLike;
import com.ruoyi.pda.service.ISocialService;

/**
 * 社交内容 控制层
 */
@RestController
@RequestMapping("/pda/social")
public class SocialController extends BaseController
{
    @Autowired
    private ISocialService socialService;

    /**
     * 查询社交内容列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Social social)
    {
        startPage();
        List<Social> list = socialService.selectSocialList(social);
        return getDataTable(list);
    }

    /**
     * 获取社交内容详细信息
     */
    @GetMapping("/{socialId}")
    public AjaxResult getInfo(@PathVariable("socialId") Long socialId)
    {
        return success(socialService.selectSocialById(socialId));
    }

    /**
     * 新增社交内容
     */
    @Log(title = "社交内容", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Social social)
    {
        // 设置当前登录用户ID
        social.setUserId(SecurityUtils.getUserId());
        return toAjax(socialService.insertSocial(social));
    }

    /**
     * 修改社交内容
     */
    @Log(title = "社交内容", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Social social)
    {
        return toAjax(socialService.updateSocial(social));
    }

    /**
     * 删除社交内容
     */
    @Log(title = "社交内容", businessType = BusinessType.DELETE)
    @DeleteMapping("/{socialIds}")
    public AjaxResult remove(@PathVariable Long[] socialIds)
    {
        // 直接使用数组，不再调用convertToStrArray方法
        return toAjax(socialService.deleteSocialByIds(socialIds));
    }

    /**
     * 点赞社交内容
     */
    @Log(title = "社交内容点赞", businessType = BusinessType.UPDATE)
    @PutMapping("/like/{socialId}")
    public AjaxResult like(@PathVariable("socialId") Long socialId)
    {
        return toAjax(socialService.likeSocial(socialId, SecurityUtils.getUserId()));
    }
    
    /**
     * 取消点赞社交内容
     */
    @Log(title = "取消社交内容点赞", businessType = BusinessType.UPDATE)
    @DeleteMapping("/unlike/{socialId}")
    public AjaxResult unlike(@PathVariable("socialId") Long socialId)
    {
        return toAjax(socialService.unlikeSocial(socialId, SecurityUtils.getUserId()));
    }
    
    /**
     * 检查用户是否已点赞社交内容
     */
    @GetMapping("/checkLike/{socialId}")
    public AjaxResult checkLike(@PathVariable("socialId") Long socialId)
    {
        return success(socialService.checkSocialLiked(socialId, SecurityUtils.getUserId()));
    }
    
    /**
     * 查询社交内容点赞列表
     */
    @GetMapping("/likes/{socialId}")
    public TableDataInfo listLikes(@PathVariable("socialId") Long socialId)
    {
        startPage();
        SocialLike socialLike = new SocialLike();
        socialLike.setSocialId(socialId);
        List<SocialLike> list = socialService.selectSocialLikeList(socialLike);
        return getDataTable(list);
    }
    
    /**
     * 查询社交内容评论列表
     */
    @GetMapping("/comments/{socialId}")
    public TableDataInfo listComments(@PathVariable("socialId") Long socialId)
    {
        startPage();
        SocialComment socialComment = new SocialComment();
        socialComment.setSocialId(socialId);
        socialComment.setParentId(0L); // 只查询一级评论
        List<SocialComment> list = socialService.selectSocialCommentList(socialComment);
        return getDataTable(list);
    }
    
    /**
     * 查询评论回复列表
     */
    @GetMapping("/replies/{commentId}")
    public AjaxResult listReplies(@PathVariable("commentId") Long commentId)
    {
        List<SocialComment> list = socialService.selectSocialCommentReplies(commentId);
        return success(list);
    }
    
    /**
     * 新增评论
     */
    @Log(title = "社交内容评论", businessType = BusinessType.INSERT)
    @PostMapping("/comment")
    public AjaxResult addComment(@RequestBody SocialComment socialComment)
    {
        // 设置当前登录用户ID
        socialComment.setUserId(SecurityUtils.getUserId());
        return toAjax(socialService.insertSocialComment(socialComment));
    }
    
    /**
     * 获取用户评论列表
     */
    @GetMapping("/userComments")
    public AjaxResult userComments()
    {
        return success(socialService.selectUserComments(SecurityUtils.getUserId()));
    }

    /**
     * 删除评论
     */
    @DeleteMapping("/comment/{commentId}")
    public AjaxResult deleteComment(@PathVariable("commentId") Long commentId)
    {
        return toAjax(socialService.deleteSocialComment(commentId));
    }

    /**
     * 获取前端所需的社交内容列表(无需权限)
     */
    @GetMapping("/frontlist")
    public AjaxResult frontList()
    {
        Social social = new Social();
        social.setStatus("0"); // 只查询正常状态的
        List<Social> list = socialService.selectSocialList(social);
        return success(list);
    }

    /**
     * 收藏社交内容
     */
    @Log(title = "社交内容收藏", businessType = BusinessType.UPDATE)
    @PutMapping("/collect/{socialId}")
    public AjaxResult collect(@PathVariable("socialId") Long socialId)
    {
        return toAjax(socialService.collectSocial(socialId, SecurityUtils.getUserId()));
    }
    
    /**
     * 取消收藏社交内容
     */
    @Log(title = "取消社交内容收藏", businessType = BusinessType.UPDATE)
    @DeleteMapping("/uncollect/{socialId}")
    public AjaxResult uncollect(@PathVariable("socialId") Long socialId)
    {
        return toAjax(socialService.uncollectSocial(socialId, SecurityUtils.getUserId()));
    }
    
    /**
     * 检查用户是否已收藏社交内容
     */
    @GetMapping("/checkCollect/{socialId}")
    public AjaxResult checkCollect(@PathVariable("socialId") Long socialId)
    {
        return success(socialService.checkSocialCollected(socialId, SecurityUtils.getUserId()));
    }
    
    /**
     * 查询用户收藏的社交内容
     */
    @GetMapping("/collections")
    public AjaxResult collections()
    {
        return success(socialService.selectUserCollections(SecurityUtils.getUserId()));
    }

    /**
     * 查询用户点赞的社交内容
     */
    @GetMapping("/userLikes")
    public AjaxResult userLikes()
    {
        return success(socialService.selectUserLikes(SecurityUtils.getUserId()));
    }
}

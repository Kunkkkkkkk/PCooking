package com.ruoyi.pda.controller;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.pda.domain.Social;
import com.ruoyi.pda.domain.SocialComment;
import com.ruoyi.pda.domain.SocialLike;
import com.ruoyi.pda.service.ISocialService;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
    @PostMapping("/status")
    public AjaxResult changeStatus(@RequestBody Social social){
        return socialService.changeStatus(social);
    }
    /**
     * 后台查询社交内容列表
     */
    @GetMapping("/list2")
    public TableDataInfo list2(Social social)
    {
        startPage();
        List<Social> list = socialService.selectSocialList2(social);
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
     * 修改社交内容（这里只更新了置顶状态）
     */
    @Log(title = "社交内容", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody Social social)
    {
        return socialService.updateSocial(social);
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
    public AjaxResult frontList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size,@RequestParam("type")String type)
    {
        Social social = new Social();
        social.setStatus("0"); // 只查询正常状态的
        social.setType(type);
        PageHelper.startPage(page, size);
        List<Social> list = socialService.selectSocialList(social);
        PageInfo<Social> pageInfo = new PageInfo<>(list);
        Map<String, Object> result = new HashMap<>();
        result.put("records", list);
        result.put("current", pageInfo.getPageNum());
        result.put("pages", pageInfo.getPages());
        result.put("total", pageInfo.getTotal());
        return success(result);
    }

    /**
     * 收藏社交内容（新版：支持收藏夹）
     */
    @Log(title = "社交内容收藏", businessType = BusinessType.UPDATE)
    @PostMapping("/collect")
    public AjaxResult collect(@RequestBody Map<String, Object> params)
    {
        Long socialId = Long.valueOf(params.get("socialId").toString());
        String folderName = params.get("folderName") != null ? params.get("folderName").toString() : "默认";
        return toAjax(socialService.collectSocialToFolder(socialId, SecurityUtils.getUserId(), folderName));
    }

    /**
     * 收藏社交内容（兼容旧版本）
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
     * 查询用户收藏的社交内容（兼容旧版本）
     */
    @GetMapping("/collections")
    public AjaxResult collections()
    {
        return success(socialService.selectUserCollections(SecurityUtils.getUserId()));
    }

    /**
     * 获取用户收藏夹列表
     */
    @GetMapping("/collection/folders")
    public AjaxResult getUserCollectionFolders()
    {
        return success(socialService.getUserCollectionFolders(SecurityUtils.getUserId()));
    }

    /**
     * 获取指定收藏夹的内容
     */
    @GetMapping("/collection/folders/{folderName}")
    public AjaxResult getFolderContent(
            @PathVariable String folderName,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize)
    {
        return success(socialService.getFolderContent(SecurityUtils.getUserId(), folderName, pageNum, pageSize));
    }

    /**
     * 创建新收藏夹并收藏
     */
    @PostMapping("/collection/folders")
    public AjaxResult createFolderAndCollect(@RequestBody Map<String, Object> params)
    {
        Long socialId = Long.valueOf(params.get("socialId").toString());
        String folderName = params.get("folderName").toString();
        return toAjax(socialService.collectSocialToFolder(socialId, SecurityUtils.getUserId(), folderName));
    }

    /**
     * 检查收藏状态（返回收藏夹信息）
     */
    @GetMapping("/collection/check/{socialId}")
    public AjaxResult checkCollectionStatus(@PathVariable Long socialId)
    {
        return success(socialService.checkCollectionStatus(socialId, SecurityUtils.getUserId()));
    }

    /**
     * 查询用户点赞的社交内容
     */
    @GetMapping("/userLikes")
    public AjaxResult userLikes()
    {
        return success(socialService.selectUserLikes(SecurityUtils.getUserId()));
    }

    @PostMapping("/uploadImage")
    public AjaxResult uploadImage(@RequestParam("imageFile") MultipartFile file)throws Exception {
        if (!file.isEmpty())
    {
        LoginUser loginUser = getLoginUser();
        String imageUrl = null;
        try {
            // 保存文件到临时目录
            String originalFilename = file.getOriginalFilename();
            // 创建一个临时文件，用于存储上传的文件
            File tempFile = File.createTempFile("upload_", originalFilename);
            // 将上传的文件转移到临时文件中
            file.transferTo(tempFile);

            // 构建请求体
            Map<String, List<String>> requestMap = new HashMap<>();
            requestMap.put("list", Collections.singletonList(tempFile.getAbsolutePath()));
            String jsonBody = new Gson().toJson(requestMap);

            // 发送请求
            String url = "http://127.0.0.1:36677/upload";
            OkHttpClient client = new OkHttpClient();
            okhttp3.RequestBody body = okhttp3.RequestBody.create(
                    MediaType.parse("application/json"), jsonBody);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            String responseData = response.body().string();

            // 解析响应
            Map map = new Gson().fromJson(responseData, Map.class);
            Boolean isSuccess = (Boolean) map.get("success");
            if (isSuccess) {
                imageUrl = ((List<String>) map.get("result")).get(0);
            } else {
                throw new Exception("上传失败");
            }

            // 清理资源
            client.dispatcher().executorService().shutdown();
            tempFile.delete();

            AjaxResult ajax = AjaxResult.success();
            ajax.put("imgUrl", imageUrl);
            return ajax;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("上传图床过程发生错误---");
            return error("上传图片失败");
        }


    }
        return error("上传图片异常，请联系管理员");}


}

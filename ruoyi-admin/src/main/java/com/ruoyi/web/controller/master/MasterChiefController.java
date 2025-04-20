package com.ruoyi.web.controller.master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.pda.domain.DTO.ChiefApplyDTO;
import com.ruoyi.pda.domain.DTO.ChiefDTO;
import com.ruoyi.pda.domain.DTO.ChiefQuery;
import com.ruoyi.pda.domain.VO.ChiefAuthVO;
import com.ruoyi.pda.domain.VO.ChiefVO;
import com.ruoyi.web.service.ChiefService;

@RestController
@RequestMapping("/master") // 添加统一的路径前缀
public class MasterChiefController extends BaseController {
    @Autowired
    private ChiefService chiefService;
    //厨师查询
    @GetMapping("/chief/pageList")
    public TableDataInfo pageList(ChiefQuery chiefQuery) {
        startPage();
        List<ChiefVO> list = chiefService.getChiefList(chiefQuery);
        return getDataTable(list);
    }
    //编辑厨师信息
    @PostMapping("/chief/edit")
    public AjaxResult edit(@Validated @RequestBody ChiefDTO chiefDTO) {
        chiefService.editChief(chiefDTO);
        return AjaxResult.success();
    }
    //绩效查询：1.总订单数 2.平均制作时间
    @GetMapping("/chief/getPerformanceData")
    public AjaxResult getPerformanceData(@RequestParam long chiefId) {
        ChiefVO chiefVO = chiefService.getPerformance(chiefId);
        return AjaxResult.success(chiefVO);
    }

    @GetMapping("/chiefAuth/list")
    public TableDataInfo chiefAuthList(ChiefQuery chiefQuery) {
        startPage();
        List<ChiefAuthVO> list = chiefService.getChiefAuthList(chiefQuery);
        return getDataTable(list);
    }

    /**
     * 用户提交厨师认证申请
     */
    @PostMapping("/chief/apply")
    public AjaxResult applyForChef(@Validated @RequestBody ChiefApplyDTO applyDTO) {
        try {
            boolean result = chiefService.applyForChef(applyDTO);
            return result ? AjaxResult.success("申请提交成功，请等待审核") : AjaxResult.error("申请提交失败");
        } catch (RuntimeException e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 获取当前用户的厨师申请信息
     */
    @GetMapping("/chief/myApplication")
    public AjaxResult getMyApplication() {
        ChiefAuthVO application = chiefService.getMyApplication();
        return AjaxResult.success(application);
    }

    /**
     * 获取当前登录用户的厨师信息（如果用户是厨师）
     */
    @GetMapping("/chief/info")
    public AjaxResult getCurrentChefInfo() {
        ChiefVO chefInfo = chiefService.getCurrentChefInfo();
        return AjaxResult.success(chefInfo);
    }
}

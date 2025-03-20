package com.ruoyi.web.controller.master;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.pda.domain.DTO.ChiefDTO;
import com.ruoyi.pda.domain.DTO.ChiefQuery;
import com.ruoyi.pda.domain.VO.ChiefAuthVO;
import com.ruoyi.pda.domain.VO.ChiefVO;
import com.ruoyi.web.service.ChiefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MasterChiefController extends BaseController {
    @Autowired
    private ChiefService chiefService;
    //厨师查询
    @GetMapping("/master/chief/pageList")
    public TableDataInfo pageList(ChiefQuery chiefQuery) {
        startPage();
        List<ChiefVO> list = chiefService.getChiefList(chiefQuery);
        return getDataTable(list);
    }
    //编辑厨师信息
    @PostMapping("/master/chief/edit")
    public AjaxResult edit(@RequestBody ChiefDTO chiefDTO) {
        AjaxResult ajaxResult = AjaxResult.success();
        chiefService.editChief(chiefDTO);
        return ajaxResult;
    }
    //绩效查询：1.总订单数 2.平均制作时间
    @GetMapping("/master/chief/getPerformanceData")
    public AjaxResult getPerformanceData(@RequestParam long chiefId) {
        AjaxResult ajaxResult = AjaxResult.success();
        ChiefVO chiefVO = chiefService.getPerformance(chiefId);
        ajaxResult.put("data", chiefVO);
        return ajaxResult;
    }

    @GetMapping("/master/chiefAuth/list")
    public TableDataInfo chiefAuthList(ChiefQuery chiefQuery) {
        startPage();
        List<ChiefAuthVO> list = chiefService.getChiefAuthList(chiefQuery);
        return getDataTable(list);
    }
}

package com.ruoyi.web.service;

import java.util.List;

import com.ruoyi.pda.domain.DTO.ChiefApplyDTO;
import com.ruoyi.pda.domain.DTO.ChiefDTO;
import com.ruoyi.pda.domain.DTO.ChiefQuery;
import com.ruoyi.pda.domain.VO.ChiefAuthVO;
import com.ruoyi.pda.domain.VO.ChiefVO;

public interface ChiefService {
    List<ChiefVO> getChiefList(ChiefQuery chiefQuery);

    void editChief(ChiefDTO chiefDTO);

    ChiefVO getPerformance(long chiefId);

    List<ChiefAuthVO> getChiefAuthList(ChiefQuery chiefQuery);

    /**
     * 提交厨师认证申请
     * @param applyDTO 申请信息
     * @return 结果
     */
    boolean applyForChef(ChiefApplyDTO applyDTO);
}

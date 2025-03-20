package com.ruoyi.web.service;

import com.ruoyi.pda.domain.DTO.ChiefDTO;
import com.ruoyi.pda.domain.DTO.ChiefQuery;
import com.ruoyi.pda.domain.VO.ChiefAuthVO;
import com.ruoyi.pda.domain.VO.ChiefVO;

import java.util.List;

public interface ChiefService {
    List<ChiefVO> getChiefList(ChiefQuery chiefQuery);

    void editChief(ChiefDTO chiefDTO);

    ChiefVO getPerformance(long chiefId);

    List<ChiefAuthVO> getChiefAuthList(ChiefQuery chiefQuery);
}

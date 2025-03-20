package com.ruoyi.web.service.impl;

import com.ruoyi.pda.domain.DTO.ChiefDTO;
import com.ruoyi.pda.domain.DTO.ChiefQuery;
import com.ruoyi.pda.domain.VO.ChiefAuthVO;
import com.ruoyi.pda.domain.VO.ChiefVO;
import com.ruoyi.pda.domain.VO.OrderHistory;
import com.ruoyi.web.mapper.ChiefMapper;
import com.ruoyi.web.service.ChiefService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChiefServiceImpl implements ChiefService {
    @Autowired
    private ChiefMapper chiefMapper;
    @Override
    public List<ChiefVO> getChiefList(ChiefQuery chiefQuery) {
        return chiefMapper.getChiefList(chiefQuery);
    }

    @Override
    public void editChief(ChiefDTO chiefDTO) {
        chiefMapper.editChief(chiefDTO);
    }

    @Override
    public ChiefVO getPerformance(long chiefId) {
        ChiefVO summary = chiefMapper.getPerformanceSummary(chiefId);
        if (summary == null) {
            summary = new ChiefVO();
            summary.setOrderNum(0);
            summary.setAverageTime(0.0);
        }
        List<OrderHistory> orderHistory = chiefMapper.getOrderHistory(chiefId);
        summary.setOrderHistory(orderHistory != null ? orderHistory : new ArrayList<>());
        return summary;
    }
    //厨师申请粉也查询
    @Override
    public List<ChiefAuthVO> getChiefAuthList(ChiefQuery chiefQuery) {
        return chiefMapper.getChiefAuthList(chiefQuery);
    }
}

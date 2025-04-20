package com.ruoyi.web.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.pda.domain.ChiefAuth;
import com.ruoyi.pda.domain.DTO.ChiefApplyDTO;
import com.ruoyi.pda.domain.DTO.ChiefDTO;
import com.ruoyi.pda.domain.DTO.ChiefQuery;
import com.ruoyi.pda.domain.VO.ChiefAuthVO;
import com.ruoyi.pda.domain.VO.ChiefVO;
import com.ruoyi.pda.domain.VO.OrderHistory;
import com.ruoyi.web.mapper.ChiefMapper;
import com.ruoyi.web.service.ChiefService;

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
    //厨师申请分页查询
    @Override
    public List<ChiefAuthVO> getChiefAuthList(ChiefQuery chiefQuery) {
        return chiefMapper.getChiefAuthList(chiefQuery);
    }

    @Override
    @Transactional
    public boolean applyForChef(ChiefApplyDTO applyDTO) {
        Long userId = SecurityUtils.getUserId();
        if (userId == null) {
            throw new RuntimeException("无法获取当前用户信息"); 
        }

        // 可以在这里添加检查，是否已经存在未审核或已通过的申请
        ChiefAuthVO existingAuth = chiefMapper.getChiefAuthByUserId(userId);
        if (existingAuth != null && ("0".equals(existingAuth.getStatus()) || "1".equals(existingAuth.getStatus()))) {
            throw new RuntimeException("您已提交申请或已是厨师，请勿重复申请");
        }

        ChiefAuth chiefAuth = new ChiefAuth();
        chiefAuth.setUserId(userId);
        chiefAuth.setRealName(applyDTO.getRealName());
        chiefAuth.setIdCode(applyDTO.getIdCode());
        chiefAuth.setLinkPhone(applyDTO.getPhone());
        chiefAuth.setYears(applyDTO.getYears());
        chiefAuth.setCuisine(applyDTO.getCuisine());
        chiefAuth.setImage(applyDTO.getImage());
        chiefAuth.setRemark(applyDTO.getRemark());

        chiefAuth.setStatus("0"); // 默认待审核
        chiefAuth.setRequestTime(LocalDateTime.now());
        
        int result = chiefMapper.insertChiefAuth(chiefAuth);
        return result > 0;
    }

    @Override
    public ChiefAuthVO getMyApplication() {
        Long userId = SecurityUtils.getUserId();
        if (userId == null) {
            // 可以根据业务逻辑返回 null 或抛出异常
             // throw new RuntimeException("无法获取当前用户信息"); 
             return null;
        }
        return chiefMapper.getChiefAuthByUserId(userId);
    }

    @Override
    public ChiefVO getCurrentChefInfo() {
        Long userId = SecurityUtils.getUserId();
        if (userId == null) {
            return null; 
        }
        // findChiefByUserId 应该只返回状态正常的厨师
        return chiefMapper.findChiefByUserId(userId);
    }
}

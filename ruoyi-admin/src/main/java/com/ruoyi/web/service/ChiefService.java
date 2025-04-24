package com.ruoyi.web.service;

import java.util.List;

import com.ruoyi.pda.domain.DTO.ChiefApplyDTO;
import com.ruoyi.pda.domain.DTO.ChiefAuthDTO;
import com.ruoyi.pda.domain.DTO.ChiefDTO;
import com.ruoyi.pda.domain.DTO.ChiefQuery;
import com.ruoyi.pda.domain.VO.ChiefAuthVO;
import com.ruoyi.pda.domain.VO.ChiefVO;
import com.ruoyi.pda.domain.VO.OrderVO;

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

    /**
     * 获取当前用户的厨师申请信息
     * @return 申请信息，如果不存在则返回null
     */
    ChiefAuthVO getMyApplication();

    /**
     * 检查当前登录用户是否为有效厨师
     * @return 如果是厨师则返回厨师信息，否则返回null
     */
    ChiefVO getCurrentChefInfo();

    /**
     * 审核通过厨师认证申请
     * @param authDTO 包含 userId 和 realName
     * @return 操作结果
     */
    boolean approveChiefAuth(ChiefAuthDTO authDTO);

    /**
     * 拒绝厨师认证申请
     * @param authDTO 包含 userId 和 reason
     * @return 操作结果
     */
    boolean rejectChiefAuth(ChiefAuthDTO authDTO);

    /**
     * 获取预约时间里可用的厨师信息
     * @param appointmentTime 预约时间
     * @return 可用的厨师信息
     */
    List<ChiefVO> getChefs(String appointmentTime);

    /**
     * 获取新订单列表
     * @return 新订单列表
     */
    List<OrderVO> getNewOrders();

    /**
     * 获取我的订单列表
     * @param userId 用户ID
     * @param status 订单状态
     * @return 我的订单列表
     */
    List<OrderVO> getMyPendingOrders(Long userId, String status);

    /**
     * 根据用户ID查询厨师信息
     * @param userId 用户ID
     * @return 厨师信息
     */
    ChiefVO findChiefByUserId(Long userId);
}

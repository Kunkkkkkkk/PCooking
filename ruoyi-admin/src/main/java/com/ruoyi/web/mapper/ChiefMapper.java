package com.ruoyi.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.ruoyi.pda.domain.ChiefAuth;
import com.ruoyi.pda.domain.DTO.ChiefAuthDTO;
import com.ruoyi.pda.domain.DTO.ChiefDTO;
import com.ruoyi.pda.domain.DTO.ChiefQuery;
import com.ruoyi.pda.domain.VO.ChiefAuthVO;
import com.ruoyi.pda.domain.VO.ChiefVO;
import com.ruoyi.pda.domain.VO.OrderHistory;
import com.ruoyi.pda.domain.VO.OrderVO;

@Mapper
public interface ChiefMapper {
    List<ChiefVO> getChiefList(ChiefQuery chiefQuery);
    @Update("UPDATE master_chief set  status=#{status}, role=#{role}, real_name=#{realName},is_recommend=#{isRecommend},`desc`=#{description} WHERE chief_id=#{chiefId}")
    void editChief(ChiefDTO chiefDTO);
    ChiefVO getPerformanceSummary(long chiefId);
    List<OrderHistory> getOrderHistory(long chiefId);
    List<ChiefAuthVO> getChiefAuthList(ChiefQuery chiefQuery);

    /**
     * 新增厨师认证申请
     * @param chiefAuth 厨师认证信息
     * @return 结果
     */
    int insertChiefAuth(ChiefAuth chiefAuth);

    /**
     * 根据用户ID查询最新的厨师认证申请
     * @param userId 用户ID
     * @return 厨师认证信息
     */
    ChiefAuthVO getChiefAuthByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID查询厨师信息 (只检查是否存在以及状态是否有效，例如已启用)
     * @param userId 用户ID
     * @return 厨师基本信息或null
     */
    ChiefVO findChiefByUserId(@Param("userId") Long userId);

    /**
     * 审核通过厨师认证申请 (更新状态为1)
     * @param userId 用户ID
     * @return 结果
     */
    int approveChiefAuth(@Param("userId") Long userId);

    /**
     * 拒绝厨师认证申请 (更新状态为2，并记录原因)
     * @param userId 用户ID
     * @param reason 拒绝原因
     * @return 结果
     */
    int rejectChiefAuth(@Param("userId") Long userId, @Param("reason") String reason);

    /**
     * 插入厨师信息 (审核通过后调用)
     * @param userId 用户ID
     * @param realName 真实姓名
     * @return 结果
     */
    int insertChief(ChiefAuthDTO chiefAuth);


    /**
     * 获取预约时间里可用的厨师信息
     * @param appointmentTime 预约时间
     * @return 可用的厨师信息
     */
    List<ChiefVO> getChefs(@Param("appointmentTime") String appointmentTime);


    /**
     * 获取新订单列表
     * @return 新订单列表
     */
    List<OrderVO> getNewOrders();

    /**
     * 获取我的订单列表
     * @param userId 用户ID
     * @return 我的订单列表
     */
    List<OrderVO> getMyPendingOrders(@Param("userId") Long userId, @Param("status") String status);

    void cancle(Long orderId);

    List<ChiefAuthVO> getChiefInfo(Long id);

    /**
     * 获取绩效统计数据
     * @param chiefId 厨师ID
     * @param timeRange 时间范围
     * @return 统计数据
     */
    Map<String, Object> getPerformanceStats(@Param("chiefId") Long chiefId, @Param("timeRange") String timeRange);

    /**
     * 获取最受欢迎菜品
     * @param chiefId 厨师ID
     * @param timeRange 时间范围
     * @return 菜品信息
     */
    Map<String, Object> getTopDish(@Param("chiefId") Long chiefId, @Param("timeRange") String timeRange);

    /**
     * 获取每日绩效明细（本周）
     * @param chiefId 厨师ID
     * @return 每日明细
     */
    List<Map<String, Object>> getDailyPerformance(@Param("chiefId") Long chiefId);
}

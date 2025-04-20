package com.ruoyi.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.ruoyi.pda.domain.ChiefAuth;
import com.ruoyi.pda.domain.DTO.ChiefDTO;
import com.ruoyi.pda.domain.DTO.ChiefQuery;
import com.ruoyi.pda.domain.VO.ChiefAuthVO;
import com.ruoyi.pda.domain.VO.ChiefVO;
import com.ruoyi.pda.domain.VO.OrderHistory;

@Mapper
public interface ChiefMapper {
    List<ChiefVO> getChiefList(ChiefQuery chiefQuery);
    @Update("UPDATE master_chief set  status=#{status}, role=#{role}, real_name=#{realName} WHERE chief_id=#{chiefId}")
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
}

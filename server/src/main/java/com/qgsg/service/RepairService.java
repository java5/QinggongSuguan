package com.qgsg.service;

import com.qgsg.dto.RepairDTO;
import com.qgsg.dto.RepairPageQueryDTO;
import com.qgsg.result.PageResult;
import com.qgsg.vo.RepairVO;

import java.util.List;

public interface RepairService {
    /**
     * 添加报修
     * @param repairDTO
     */
    void insertRepair(RepairDTO repairDTO);

    /**
     * 报修分页查询
     * @param repairPageQueryDTO
     * @return
     */

    PageResult page(RepairPageQueryDTO repairPageQueryDTO);

    /**
     * 根据报修id查询报修信息用于修改报修的页面回显便于修改
     * @param id
     * @return
     */
    RepairVO getDormitory(int id);

    /**
     * 修改报修信息
     * @param repairDTO
     */

    void update(RepairDTO repairDTO);

    /**
     * 报修批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 根据id修改报修状态
     * @param id
     */
    void updateStatus(int id);

    /**
     * 首页顶部：报修统计
     */
    int showRepairNum();
}

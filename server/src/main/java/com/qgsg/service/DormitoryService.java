package com.qgsg.service;

import com.qgsg.dto.DormitoryDTO;
import com.qgsg.dto.DormitoryPageQueryDTO;
import com.qgsg.result.PageResult;
import com.qgsg.vo.DormitoryVO;

import java.util.List;

public interface DormitoryService {
    /**
     * 宿舍分页查询
     * @param dormitoryPageQueryDTO
     * @return
     */
    PageResult page(DormitoryPageQueryDTO dormitoryPageQueryDTO);

    /**
     * 新增宿舍
     * @param dormitoryDTO
     */
    void saveDormitory(DormitoryDTO dormitoryDTO);

    /**
     * 根据宿舍Id查询宿舍信息
     * @param id
     * @return
     */
    DormitoryVO getDormitory(int id);

    /**
     * 修改宿舍
     * @param dormitoryDTO
     */
    void update(DormitoryDTO dormitoryDTO);

    /**
     * 宿舍批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);


    /**
     * 首页Echarts 查询所有宿舍号用于条形图的x轴显示
     */
    List<String> getDormitoryNumber();

    /**
     * 主页：住宿人数
     * @return
     */
    Long selectHaveRoomStudentNum();
    /**
     * 首页顶部：空宿舍统计
     */
    int emptydormitory();
}

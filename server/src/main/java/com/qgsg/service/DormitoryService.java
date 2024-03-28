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
}

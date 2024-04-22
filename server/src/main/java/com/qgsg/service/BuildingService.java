package com.qgsg.service;

import com.qgsg.dto.BuildingDTO;
import com.qgsg.dto.BuildingPageQueryDTO;
import com.qgsg.result.PageResult;

public interface BuildingService {
    /**
     * 宿舍楼分页查询
     * @param buildingPageQueryDTO
     * @return
     */
    PageResult page(BuildingPageQueryDTO buildingPageQueryDTO);
    /**
     * 新增宿舍楼
     * @param buildingDTO
     * @return
     */
    void saveDormitoryBuilding(BuildingDTO buildingDTO);

    /**
     * 修改宿舍楼
     * @param buildingDTO
     */
    void update(BuildingDTO buildingDTO);

    /**
     * 宿舍楼删除
     * @param id
     */
    void deleteBatch(int id);
}

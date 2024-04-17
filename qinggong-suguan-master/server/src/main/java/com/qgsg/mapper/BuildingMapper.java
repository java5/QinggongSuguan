package com.qgsg.mapper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qgsg.dto.BuildingPageQueryDTO;
import com.qgsg.dto.DormitoryPageQueryDTO;
import com.qgsg.entity.Building;
import com.qgsg.result.PageResult;
import com.qgsg.vo.BuildingVO;
import com.qgsg.vo.DormitoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BuildingMapper {
    /**
     * 宿舍楼分页查询
     * @param buildingPageQueryDTO
     * @return
     */
    Page<BuildingVO> selectpage(BuildingPageQueryDTO buildingPageQueryDTO);
    /**
     * 新增宿舍楼
     * @param building
     * @return
     */
    void insert(Building building);

    /**
     * 修改宿舍楼
     * @param building
     */
    void updateBuilding(Building building);

    /**
     * 删除楼
     * @param id
     */
    void delete(int id);

    /**
     * 根据楼号查楼
     * @param buildingNumber
     * @return
     */
    @Select("select * from dorbuilding where building_number=#{buildingNumber}")
    Building select(String buildingNumber);
}

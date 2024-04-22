package com.qgsg.mapper;

import com.github.pagehelper.Page;
import com.qgsg.dto.DormitoryDTO;
import com.qgsg.dto.DormitoryPageQueryDTO;
import com.qgsg.entity.Dormitory;
import com.qgsg.vo.DormitoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DormitoryMapper {

    /**
     * 根据宿舍号查宿舍信息
     * @param dormitoryNumber
     */
    @Select("select * from dormitory where dormitory_number = #{dormitoryNumber}")
    Dormitory selectDormitory(String dormitoryNumber);
    @Update("update dormitory set actual_capacity= #{i}")
    void updateNumberOfPeople(int i);

    /**
     * 宿舍分页查询
     * @param dormitoryPageQueryDTO
     * @return
     */
    Page<DormitoryVO> selectpage(DormitoryPageQueryDTO dormitoryPageQueryDTO);

    /**
     * 新增宿舍
     * @param dormitoryDTO
     */
    void insertdormitory(DormitoryDTO dormitoryDTO);

    /**
     * 根据宿舍Id查询宿舍用于修改宿舍的页面回显便于修改
     * @param id
     * @return
     */
    @Select("select * from dormitory where id=#{id}")
    Dormitory selectDormitoryMessage(int id);

    @Select("select * from dormitory where dormitory_number=#{number}")
    Dormitory getByNumber(String number);

    /**
     * 修改宿舍
     * @param dormitory
     */
    void updateDormitory(Dormitory dormitory);

    /**
     * 批量删除宿舍
     * @param ids
     */
    void deleteByIds(List<Long> ids);

    /**
     * 首页Echarts 查询所有宿舍号用于条形图的x轴显示
     */
    @Select("select dormitory_number from dormitory")
    List<String> selectAll();
    /**
     * 主页 住宿人数
     */
    @Select("select sum(actual_capacity) from dormitory")
    Long selectAllDormitoryStudentNum();
    /**
     * 首页顶部：空宿舍统计
     */
    @Select("select count(id) from dormitory where actual_capacity=#{actualCapacity}")
    int selectEmptyDormitory(int actualCapacity);

    /**
     * 根据宿舍楼id查询宿舍信息
     * @param buildingId
     * @return
     */
    @Select("select * from dormitory where building_id=#{buildingId}")
    List<Dormitory> selectByBuildingId(int buildingId);
}

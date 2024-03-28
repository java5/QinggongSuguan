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
}

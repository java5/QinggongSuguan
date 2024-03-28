package com.qgsg.mapper;

import com.qgsg.entity.Dormitory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
}

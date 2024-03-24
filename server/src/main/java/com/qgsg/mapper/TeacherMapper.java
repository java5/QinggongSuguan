package com.qgsg.mapper;

import com.qgsg.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeacherMapper {

    /**
     * 根据用户名查询管理员老师
     * @param username
     * @return
     */
    @Select("select * from teacher where username = #{username}")
    Teacher getByUsername(String username);

}

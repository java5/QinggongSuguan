package com.qgsg.mapper;

import com.qgsg.entity.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TeacherMapper {

    /**
     * 根据用户名查询管理员老师
     * @param username
     * @return
     */
    @Select("select * from housemaster where username = #{username}")
    Teacher getByUsername(String username);

    /**
     * 插入管理员数据
     * @param teacher
     */
    @Insert("insert into housemaster (name, username, password, phone,authority) " +
            "values " +
            "(#{name},#{username},#{password},#{phone},#{authority})")
    void insert(Teacher teacher);
}

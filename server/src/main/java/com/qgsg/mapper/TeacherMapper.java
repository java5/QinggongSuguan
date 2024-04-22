package com.qgsg.mapper;

import com.github.pagehelper.Page;
import com.qgsg.dto.TeacherPageQueryDTO;
import com.qgsg.entity.Teacher;
import org.apache.ibatis.annotations.Delete;
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
     *
     * @param teacher
     * @return
     */
    @Insert("insert into housemaster (name, username, password, phone,authority) " +
            "values " +
            "(#{name},#{username},#{password},#{phone},#{authority})")
    int insert(Teacher teacher);

    /**
     * 更新管理员数据
     * @param teacher
     */
    void update(Teacher teacher);


    /**
     * 删除管理员功能
     * @param id
     */
    @Delete("delete from housemaster where id=#{id}")
    void deleteById(int id);

    //分页
    Page<Teacher> page(TeacherPageQueryDTO teacherPageQueryDTO);

    /**
     * 获取个人信息
     * @return
     */
    @Select("select * from housemaster where id = #{id}")
    Teacher selectById(Long id);
}

package com.qgsg.mapper;

import com.github.pagehelper.Page;
import com.qgsg.dto.StudentPageQueryDTO;
import com.qgsg.entity.Student;
import com.qgsg.vo.StudentVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {

    /**
     * 插入学生数据
     * @param student
     */
    void insert(Student student);

    /**
     * 修改学生数据
     * @param student
     */
    void update(Student student);


    /**
     * 学生分页查询
     * @param studentPageQueryDTO
     * @return
     */
    Page<StudentVO> pageQuery(StudentPageQueryDTO studentPageQueryDTO);

    /**
     * 根据学生查询学生
     * @param number
     * @return
     */
    @Select("select * from student where number = #{number}")
    Student getByNumber(String number);

    /**
     * 根据id删学生
     * @param number
     */
    @Delete("delete from student where number=#{number}")
    void deleteByNumber(String number);
}

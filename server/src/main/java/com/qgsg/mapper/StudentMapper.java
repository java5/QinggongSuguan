package com.qgsg.mapper;

import com.github.pagehelper.Page;
import com.qgsg.dto.StudentPageQueryDTO;
import com.qgsg.entity.Student;
import com.qgsg.vo.StudentVO;
import org.apache.ibatis.annotations.Mapper;

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


    Page<StudentVO> pageQuery(StudentPageQueryDTO studentPageQueryDTO);

}

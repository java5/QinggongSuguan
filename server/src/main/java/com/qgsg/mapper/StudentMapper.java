package com.qgsg.mapper;

import com.qgsg.entity.Student;
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
}

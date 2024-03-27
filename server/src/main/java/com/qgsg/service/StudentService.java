package com.qgsg.service;

import com.qgsg.dto.StudentDTO;
import com.qgsg.dto.StudentPageQueryDTO;
import com.qgsg.entity.Student;
import com.qgsg.result.PageResult;

public interface StudentService {
    /**
     * 新增学生
     * @param studentDTO
     */
    void saveStudent(StudentDTO studentDTO);



    /**
     * 修改学生信息
     * @param studentDTO
     */
    void updateStudent(StudentDTO studentDTO);

    /**
     * 学生分页查询
     * @param studentPageQueryDTO
     */
    PageResult page(StudentPageQueryDTO studentPageQueryDTO);

    /**
     * 根据学号查询
     * @param number
     * @return
     */
    Student getByNumber(String number);
}

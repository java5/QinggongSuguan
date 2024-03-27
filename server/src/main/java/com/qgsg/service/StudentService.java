package com.qgsg.service;

import com.qgsg.dto.StudentDTO;
import com.qgsg.dto.StudentPageQueryDTO;
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
     * 查找学生
     * @param studentPageQueryDTO
     */
    PageResult page(StudentPageQueryDTO studentPageQueryDTO);
}

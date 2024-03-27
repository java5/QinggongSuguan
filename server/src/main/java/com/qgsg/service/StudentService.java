package com.qgsg.service;

import com.qgsg.dto.StudentDTO;

public interface StudentService {
    /**
     * 新增学生
     * @param studentDTO
     */
    void saveStudent(StudentDTO studentDTO);

    /**
     * 查找学生
     * @param studentDTO
     */
    void search(StudentDTO studentDTO);

    /**
     * 修改学生信息
     * @param studentDTO
     */
    void updateStudent(StudentDTO studentDTO);
}

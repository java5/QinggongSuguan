package com.qgsg.service;

import com.qgsg.dto.StudentDTO;

public interface StudentService {
    /**
     * 新增学生
     * @param studentDTO
     */
    void saveStudent(StudentDTO studentDTO);
}

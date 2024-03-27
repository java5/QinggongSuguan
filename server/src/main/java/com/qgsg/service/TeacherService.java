package com.qgsg.service;

import com.qgsg.dto.StudentDTO;
import com.qgsg.dto.TeacherDTO;
import com.qgsg.dto.TeacherLoginDTO;
import com.qgsg.entity.Teacher;

public interface TeacherService {

    /**
     * 教师登录
     * @param teacherLoginDTO
     * @return
     */
    Teacher login(TeacherLoginDTO teacherLoginDTO);


    /**
     * 注册管理员
     * @param teacherDTO
     */
    void save(TeacherDTO teacherDTO);

    /**
     * 修改管理员信息
     * @param teacherDTO
     */
    void updateTeacher(TeacherDTO teacherDTO);
}

package com.qgsg.service;

import com.qgsg.dto.TeacherLoginDTO;
import com.qgsg.entity.Teacher;

public interface TeacherService {

    /**
     * 教师登录
     * @param teacherLoginDTO
     * @return
     */
    Teacher login(TeacherLoginDTO teacherLoginDTO);

}

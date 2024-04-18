package com.qgsg.service;

import com.qgsg.dto.TeacherDTO;
import com.qgsg.dto.TeacherLoginDTO;
import com.qgsg.dto.TeacherPageQueryDTO;
import com.qgsg.entity.Teacher;
import com.qgsg.result.PageResult;

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

    /**
     * 删除管理员功能
     * @param id
     */
    void deleteTeacher(int id);

    /**
     * fenye
     * @param teacherPageQueryDTO
     * @return
     */
    PageResult page(TeacherPageQueryDTO teacherPageQueryDTO);
}

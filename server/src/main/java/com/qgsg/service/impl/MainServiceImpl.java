package com.qgsg.service.impl;

import com.qgsg.entity.Teacher;
import com.qgsg.mapper.TeacherMapper;
import com.qgsg.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainServiceImpl implements MainService {
    @Autowired
    private TeacherMapper teacherMapper;
    /**
     * 个人信息
     * @param id
     * @return
     */
    @Override
    public Teacher getMessage(Long id) {
        Teacher teacher = teacherMapper.selectById(id);
        return teacher;
    }
}

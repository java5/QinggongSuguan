package com.qgsg.service.impl;

import com.qgsg.constant.MessageConstant;
import com.qgsg.constant.StatusConstant;
import com.qgsg.controller.admin.MqttController;
import com.qgsg.dto.TeacherDTO;
import com.qgsg.dto.TeacherLoginDTO;
import com.qgsg.entity.Teacher;
import com.qgsg.exception.AccountLockedException;
import com.qgsg.exception.AccountNotFoundException;
import com.qgsg.exception.PasswordErrorException;
import com.qgsg.mapper.TeacherMapper;
import com.qgsg.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;



    /**
     * 教师登录
     *
     * @param teacherLoginDTO
     * @return
     */
    public Teacher login(TeacherLoginDTO teacherLoginDTO) {
        String username = teacherLoginDTO.getUsername();
        String password = teacherLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Teacher teacher = teacherMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (teacher == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        //password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(teacher.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

//        if (teacher.getStatus() == StatusConstant.DISABLE) {
//            //账号被锁定
//            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
//        }


        //3、返回实体对象
        return teacher;
    }


    /**
     * 注册管理员
     * @param teacherDTO
     */
    @Override
    public void save(TeacherDTO teacherDTO) {
        Teacher teacher= new Teacher();
        BeanUtils.copyProperties(teacherDTO,teacher);
        teacherMapper.insert(teacher);
    }

}

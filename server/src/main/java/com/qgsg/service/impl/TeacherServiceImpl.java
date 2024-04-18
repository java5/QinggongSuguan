package com.qgsg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qgsg.constant.MessageConstant;
import com.qgsg.dto.TeacherDTO;
import com.qgsg.dto.TeacherLoginDTO;
import com.qgsg.dto.TeacherPageQueryDTO;
import com.qgsg.entity.Teacher;
import com.qgsg.exception.AccountNotFoundException;
import com.qgsg.exception.PasswordErrorException;
import com.qgsg.exception.UsernameExistException;
import com.qgsg.mapper.TeacherMapper;
import com.qgsg.result.PageResult;
import com.qgsg.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

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
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(teacher.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        //3、返回实体对象
        return teacher;
    }


    /**
     * 注册管理员
     * @param teacherDTO
     */
    @Override
    public void save(TeacherDTO teacherDTO) {
        Teacher Username = teacherMapper.getByUsername(teacherDTO.getUsername());
        if(Username!=null){
            throw new UsernameExistException(MessageConstant.ACCOUNT_FOUND);
        }else {
            Teacher teacher= new Teacher();
            BeanUtils.copyProperties(teacherDTO,teacher);
            teacherMapper.insert(teacher);
        }
    }


    /**
     * 修改管理员信息
     * @param teacherDTO
     */
    @Override
    public void updateTeacher(TeacherDTO teacherDTO) {
        Teacher teacher= new Teacher();
        BeanUtils.copyProperties(teacherDTO,teacher);
        teacherMapper.update(teacher);
    }

    /**
     * 删除管理员
     * @param id
     */
    @Override
    public void deleteTeacher(int id) {
        teacherMapper.deleteById(id);
    }

    /**
     * 分页
     * @param teacherPageQueryDTO
     * @return
     */
    @Override
    public PageResult page(TeacherPageQueryDTO teacherPageQueryDTO) {
        PageHelper.startPage(teacherPageQueryDTO.getPage(), teacherPageQueryDTO.getPageSize());
        Page<Teacher> page = teacherMapper.page(teacherPageQueryDTO);
        long total = page.getTotal();
        List<Teacher> records = page.getResult();
        return new PageResult(total, records);
    }
}

package com.qgsg.service.impl;

import com.qgsg.dto.StudentDTO;
import com.qgsg.entity.Student;
import com.qgsg.mapper.StudentMapper;
import com.qgsg.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;



    /**
     * 新增学生
     * @param studentDTO
     */
    @Override
    public void saveStudent(StudentDTO studentDTO) {
        Student student = new Student();
        if (student.getFinger_print() == null) {
            student.setFinger_print("指纹信息");
        }
        student.setSign_time(LocalDateTime.now());
        student.setSign_status(0);
        BeanUtils.copyProperties(studentDTO,student);
        log.info("{}",student);
        studentMapper.insert(student);
    }




    /**
     * 根据学号查学生
     * @param studentDTO
     */
    @Override
    public void search(StudentDTO studentDTO) {

    }


    /**
     * 修改学生信息
     * @param studentDTO
     */
    @Override
    public void updateStudent(StudentDTO studentDTO) {
        Student student= new Student();
        BeanUtils.copyProperties(studentDTO,student);
        studentMapper.update(student);
    }
}

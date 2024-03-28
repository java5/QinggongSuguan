package com.qgsg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qgsg.constant.MessageConstant;
import com.qgsg.dto.StudentDTO;
import com.qgsg.dto.StudentPageQueryDTO;
import com.qgsg.entity.Dormitory;
import com.qgsg.entity.Student;
import com.qgsg.exception.InsufficientCapacityException;
import com.qgsg.mapper.DormitoryMapper;
import com.qgsg.mapper.StudentMapper;
import com.qgsg.result.PageResult;
import com.qgsg.service.StudentService;
import com.qgsg.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private DormitoryMapper dormitoryMapper;

    /**
     * 新增学生
     * @param studentDTO
     */
    @Override
    public void saveStudent(StudentDTO studentDTO) {
        Student student = new Student();
        if (student.getFingerPrint() == null) {
            student.setFingerPrint("默认指纹信息");
        }
        student.setSignTime(LocalDateTime.now());
        student.setSignStatus(0);
        Dormitory dormitory=dormitoryMapper.selectDormitory(studentDTO.getDormitoryNumber());
        if(dormitory.getActualCapacity()>=dormitory.getAccommodationCapacity())
        {
            throw new InsufficientCapacityException(MessageConstant.CAPACITY_NOT_ADEQUATE);
        }else {
            dormitoryMapper.updateNumberOfPeople(dormitory.getActualCapacity()+1);
            BeanUtils.copyProperties(studentDTO,student);
            log.info("{}",student);
            studentMapper.insert(student);
        }
    }
//    @Override
//    public void saveStudent(StudentDTO studentDTO) {
//        Student student = new Student();
//        BeanUtils.copyProperties(studentDTO,student);
//        student.setSignTime(LocalDateTime.now());
//        student.setSignStatus(0);
//        log.info("{}",student);
//        studentMapper.insert(student);
//    }

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

    /**
     * 根据学号查学生
     * @param studentPageQueryDTO
     */
    @Override
    public PageResult page(StudentPageQueryDTO studentPageQueryDTO) {
        PageHelper.startPage(studentPageQueryDTO.getPage(),studentPageQueryDTO.getPageSize());
        Page<StudentVO> studentVO = studentMapper.pageQuery(studentPageQueryDTO);
        return new PageResult(studentVO.getTotal(), studentVO.getResult());
    }


    /**
     * 根据学号查学生
     * @param number
     * @return
     */
    @Override
    public Student getByNumber(String number) {
        Student student=studentMapper.getByNumber(number);
        log.info("{}",student);
        return student;
    }

    /**
     * 批量删学生
     * @param numbers
     */
    @Override
    public void deleteStudent(List<String> numbers) {
        log.info("number:{}",numbers);
        for(String number:numbers){
            log.info(number);
            studentMapper.deleteByNumber(number);
        }
    }
}

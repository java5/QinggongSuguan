package com.qgsg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qgsg.constant.MessageConstant;
import com.qgsg.dto.StudentDTO;
import com.qgsg.dto.StudentPageQueryDTO;
import com.qgsg.entity.Dormitory;
import com.qgsg.entity.Sign;
import com.qgsg.entity.Student;
import com.qgsg.exception.InsufficientCapacityException;
import com.qgsg.exception.UsernameExistException;
import com.qgsg.mapper.DormitoryMapper;
import com.qgsg.mapper.SignMapper;
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
     * 保存学生信息到数据库。
     * 将学生DTO（数据传输对象）转换为学生实体，设置学生的签到时间和状态。
     * 并对学生所住宿舍的信息进行校验，若宿舍不存在或宿舍已满，则抛出异常。
     * 否则，更新宿舍的住宿人数，将学生信息填充到学生实体中并保存到数据库。
     *
     * @param studentDTO 学生的数据传输对象，包含学生的详细信息和宿舍号。
     * @throws InsufficientCapacityException 如果宿舍不存在或宿舍容量不足，则抛出此异常。
     */
    @Override
    public void saveStudent(StudentDTO studentDTO) {
        Student student = new Student();
        // 设置学生的签到时间和状态
        student.setSignTime(LocalDateTime.now());
        student.setSignStatus(0);//新添加的默认状态为0

        // 根据宿舍号查询宿舍信息
        Dormitory dormitory = dormitoryMapper.selectDormitory(studentDTO.getDormitoryNumber());
        log.info("dormitory:{}", dormitory);

        String dorNumber = dormitory.getDormitoryNumber();
         int number = dormitory.getActualCapacity();//查到的宿舍实际人数
        log.info("添加前宿舍的实际人数为：{}",number);


        // 宿舍不存在或宿舍已满时抛出异常
        if (dormitory == null) {
            throw new InsufficientCapacityException(MessageConstant.CAPACITY_NOT_ADEQUATE);
        }
        if (dormitory.getActualCapacity() >= dormitory.getAccommodationCapacity()) {
            throw new InsufficientCapacityException(MessageConstant.CAPACITY_NOT_ADEQUATE);
        } else {
            number++;
            log.info("即将添加...添加学生后number为：{},宿舍号为：{}",number,dorNumber);
            // 更新宿舍住宿人数
//            dormitoryMapper.updateNumberOfPeople(dormitory.getActualCapacity() + 1);
            dormitoryMapper.updateNumber(number,dorNumber);
            // 将学生DTO的信息复制到学生实体中
            BeanUtils.copyProperties(studentDTO, student);
            log.info("{}", student);
            // 插入学生信息到数据库
            studentMapper.insert(student);
        }
        // 插入学生的签到信息到数据库
//        studentMapper.insertsign(student);
    }


    @Autowired
    private SignMapper signMapper;
    /**
     * 修改学生信息
     * @param studentDTO
     */
    @Override
    public void updateStudent(StudentDTO studentDTO) {
        Student student= new Student();
        Sign sign=new Sign();
        BeanUtils.copyProperties(studentDTO,student);
        BeanUtils.copyProperties(studentDTO,sign);
        studentMapper.update(student);
        signMapper.update(sign);
    }

    /**
     * 分页查学生
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
        if(numbers == null) throw new UsernameExistException(MessageConstant.XUEHAOKONG);
        for(String number:numbers){
            log.info(number);
            studentMapper.deleteByNumber(number);
            signMapper.deleteToSign1(number);
        }
    }

    @Override
    public List<Student> getByDornumber(String dormitoryNumber) {
        List<Student> student=studentMapper.getByDornumer(dormitoryNumber);
        return student;
    }


    /**
     * 主页顶部：学生统计
     */
    @Override
    public int studentNum() {
        int studentNum=studentMapper.selectAllNum();
        return studentNum;
    }
    /**
     * 获取所有学生信息
     * @return
     */
    @Override
    public List<Student> getStudtentAll() {
        return studentMapper.findAll();
    }
}

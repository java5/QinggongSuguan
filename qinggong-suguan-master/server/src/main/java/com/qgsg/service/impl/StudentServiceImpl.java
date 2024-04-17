package com.qgsg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qgsg.config.mqtt.MqttInboundConfiguration;
import com.qgsg.constant.MessageConstant;
import com.qgsg.context.BaseContext;
import com.qgsg.dto.MqttDTO;
import com.qgsg.dto.StudentDTO;
import com.qgsg.dto.StudentPageQueryDTO;
import com.qgsg.entity.Building;
import com.qgsg.entity.Dormitory;
import com.qgsg.entity.Sign;
import com.qgsg.entity.Student;
import com.qgsg.exception.InsufficientCapacityException;
import com.qgsg.mapper.BuildingMapper;
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
import java.util.prefs.BackingStoreException;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private DormitoryMapper dormitoryMapper;
    @Autowired
    private BuildingMapper buildingMapper;
    @Autowired
    private SignMapper signMapper;

    /**
     * 新增学生
     * @param studentDTO
     */
    @Override
    public void saveStudent(StudentDTO studentDTO) {
        Student student1 = studentMapper.selectByNumber(studentDTO.getNumber());
        if (student1 != null) {
            if (student1.getNumber() != null) {
                throw new InsufficientCapacityException("学号重复，请重新输入！");
            }
        } else {
            if (studentDTO.getSex().equals("男")) {
                Dormitory dormitory = dormitoryMapper.selectDormitory(studentDTO.getDormitoryNumber());
                if (dormitory == null) {
                    throw new InsufficientCapacityException("该宿舍不存在，请重试！");
                }
                studentDTO.setDormitoryId(dormitory.getId());
                Building building1 = buildingMapper.select(studentDTO.getBuildingNumber());
                if (building1 == null) {
                    throw new InsufficientCapacityException("该宿舍楼不存在，请重试！");
                }
                Building building = buildingMapper.select(dormitory.getBuildingNumber());
                if (building.getBuildingNumber().equals(studentDTO.getBuildingNumber())) {
                    if (building != null) {
                        if (building.getBuildingDetail().equals("女宿舍")) {
                            throw new InsufficientCapacityException("该宿舍是女生宿舍,请重新选择！");
                        }
                    } else {
                        throw new InsufficientCapacityException("宿舍号与楼号不匹配,请重试!");
                    }
                } else {
                    throw new InsufficientCapacityException("宿舍号与楼号不匹配,请重试!");
                }
                Student student = new Student();
                student.setSignStatus(0);
                if (dormitory.getActualCapacity() >= dormitory.getAccommodationCapacity()) {
                    throw new InsufficientCapacityException(MessageConstant.CAPACITY_NOT_ADEQUATE);
                } else {
                    dormitoryMapper.updateNumberOfPeople(dormitory.getActualCapacity() + 1, dormitory.getDormitoryNumber());
                    BeanUtils.copyProperties(studentDTO, student);
                    log.info("{}", student);
                    studentMapper.insert(student);
                }
                //  studentMapper.insertsign(student);
            } else {
                Dormitory dormitory = dormitoryMapper.selectDormitory(studentDTO.getDormitoryNumber());
                if (dormitory == null) {
                    throw new InsufficientCapacityException("该宿舍不存在，请重试！");
                }
                studentDTO.setDormitoryId(dormitory.getId());
                Building building1 = buildingMapper.select(studentDTO.getBuildingNumber());

                if (building1 == null) {
                    throw new InsufficientCapacityException("该宿舍楼不存在，请重试！");
                }
                Building building = buildingMapper.select(dormitory.getBuildingNumber());
                if (building.getBuildingNumber().equals(studentDTO.getBuildingNumber())) {
                    if (building != null) {
                        if (building.getBuildingDetail().equals("男宿舍")) {
                            throw new InsufficientCapacityException("该宿舍是男生宿舍,请重新选择！");
                        }
                    } else {
                        throw new InsufficientCapacityException("宿舍号与楼号不匹配,请重试!");
                    }
                } else {
                    throw new InsufficientCapacityException("宿舍号与楼号不匹配,请重试!");
                }
                Student student = new Student();
                student.setSignStatus(0);
                if (dormitory.getActualCapacity() >= dormitory.getAccommodationCapacity()) {
                    throw new InsufficientCapacityException(MessageConstant.CAPACITY_NOT_ADEQUATE);
                } else {
                    dormitoryMapper.updateNumberOfPeople(dormitory.getActualCapacity() + 1, dormitory.getDormitoryNumber());
                    BeanUtils.copyProperties(studentDTO, student);
                    log.info("{}", student);
                    studentMapper.insert(student);
                }
            }
        }
    }

//        if (studentDTO.getFingerPrint() == null) {
//            System.out.println(MqttInboundConfiguration.mqttDTO.getFingerPrint()+"=============================");
//            studentDTO.setFingerPrint(MqttInboundConfiguration.mqttDTO.getFingerPrint());
//        }
       // student.setSignTime(LocalDateTime.now());





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
        Student student1 = studentMapper.selectById(studentDTO.getId());
        if (!student1.getNumber().equals(studentDTO.getNumber())) {
            Student student = studentMapper.selectByNumber(studentDTO.getNumber());
            if(student!=null) {
                throw new InsufficientCapacityException("学号重复，请重新输入！");
            }
        } else {
            if (studentDTO.getSex().equals("男")) {
                Dormitory dormitory = dormitoryMapper.selectDormitory(studentDTO.getDormitoryNumber());
                if (dormitory == null) {
                    throw new InsufficientCapacityException("该宿舍不存在，请重试！");
                }
                studentDTO.setDormitoryId(dormitory.getId());
                Building building1 = buildingMapper.select(studentDTO.getBuildingNumber());
                if (building1 == null) {
                    throw new InsufficientCapacityException("该宿舍楼不存在，请重试！");
                }
                Building building = buildingMapper.select(dormitory.getBuildingNumber());
                if (building.getBuildingNumber().equals(studentDTO.getBuildingNumber())) {
                    if (building != null) {
                        if (building.getBuildingDetail().equals("女宿舍")) {
                            throw new InsufficientCapacityException("该宿舍是女生宿舍,请重新选择！");
                        }
                    } else {
                        throw new InsufficientCapacityException("宿舍号与楼号不匹配,请重试!");
                    }
                } else {
                    throw new InsufficientCapacityException("宿舍号与楼号不匹配,请重试!");
                }
                Student student = new Student();
                student.setSignStatus(0);
                if (dormitory.getActualCapacity() >= dormitory.getAccommodationCapacity()) {
                    throw new InsufficientCapacityException(MessageConstant.CAPACITY_NOT_ADEQUATE);
                } else {
                    dormitoryMapper.updateNumberOfPeople(dormitory.getActualCapacity() + 1, dormitory.getDormitoryNumber());
                    BeanUtils.copyProperties(studentDTO, student);
                    log.info("{}", student);
                    studentMapper.insert(student);
                }
                //  studentMapper.insertsign(student);
            } else {
                Dormitory dormitory = dormitoryMapper.selectDormitory(studentDTO.getDormitoryNumber());
                if (dormitory == null) {
                    throw new InsufficientCapacityException("该宿舍不存在，请重试！");
                }
                studentDTO.setDormitoryId(dormitory.getId());
                Building building1 = buildingMapper.select(studentDTO.getBuildingNumber());

                if (building1 == null) {
                    throw new InsufficientCapacityException("该宿舍楼不存在，请重试！");
                }
                Building building = buildingMapper.select(dormitory.getBuildingNumber());
                if (building.getBuildingNumber().equals(studentDTO.getBuildingNumber())) {
                    if (building != null) {
                        if (building.getBuildingDetail().equals("男宿舍")) {
                            throw new InsufficientCapacityException("该宿舍是男生宿舍,请重新选择！");
                        }
                    } else {
                        throw new InsufficientCapacityException("宿舍号与楼号不匹配,请重试!");
                    }
                } else {
                    throw new InsufficientCapacityException("宿舍号与楼号不匹配,请重试!");
                }
                Student student = new Student();
                //student.setSignStatus(0);
                Student student2 = studentMapper.selectById(studentDTO.getId());
                Dormitory dormitory1 = dormitoryMapper.selectDormitory(student2.getDormitoryNumber());
                if (studentDTO.getDormitoryNumber().equals(student2.getDormitoryNumber())) {
                    BeanUtils.copyProperties(studentDTO, student);
                    log.info("{}", student);
                    studentMapper.update(student);

                } else {
                    if (dormitory.getActualCapacity() >= dormitory.getAccommodationCapacity()) {
                        throw new InsufficientCapacityException(MessageConstant.CAPACITY_NOT_ADEQUATE);
                    } else {
                        dormitoryMapper.updateNumberOfPeople(dormitory.getActualCapacity() + 1, dormitory.getDormitoryNumber());
                        dormitoryMapper.updateNumberOfPeople(dormitory1.getActualCapacity()-1,dormitory1.getDormitoryNumber());
                        BeanUtils.copyProperties(studentDTO, student);
                        log.info("{}", student);
                        student.setDormitoryId(dormitory.getId());
                        studentMapper.update(student);
                    }
                }
            }
        }
    }
//        Student student= new Student();
//        Sign sign=new Sign();
//        BeanUtils.copyProperties(studentDTO,student);
//        BeanUtils.copyProperties(studentDTO,sign);




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


//    /**
//     * 根据学号查学生
//     * @param number
//     * @return
//     */
//    @Override
//    public Student getByNumber(String number) {
//        Student student=studentMapper.getByNumber(number);
//        log.info("{}",student);
//        return student;
//    }

    /**
     * 批量删学生
     * @param id
     */
    @Override
    public void deleteStudent(int id) {
        log.info("number:{}",id);
        Student student = studentMapper.getById(id);
        Dormitory dormitory = dormitoryMapper.selectDormitory(student.getDormitoryNumber());
        int actualCapacity = dormitory.getActualCapacity()-1;
        dormitoryMapper.updateNumberOfPeople(actualCapacity,student.getDormitoryNumber());
        studentMapper.deleteByNumber(id);
           // signMapper.deleteToSign(number);

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

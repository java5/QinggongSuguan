package com.qgsg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qgsg.constant.MessageConstant;
import com.qgsg.dto.DormitoryDTO;
import com.qgsg.dto.DormitoryPageQueryDTO;
import com.qgsg.entity.Building;
import com.qgsg.entity.Dormitory;
import com.qgsg.entity.Student;
import com.qgsg.exception.AccountNotFoundException;
import com.qgsg.exception.DeletionNotAllowedException;
import com.qgsg.exception.InsufficientCapacityException;
import com.qgsg.mapper.BuildingMapper;
import com.qgsg.mapper.DormitoryMapper;
import com.qgsg.mapper.StudentMapper;
import com.qgsg.result.PageResult;
import com.qgsg.service.DormitoryService;
import com.qgsg.vo.DormitoryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DormitoryServiceImpl implements DormitoryService {
    @Autowired
    private DormitoryMapper dormitoryMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private BuildingMapper buildingMapper;

    /**
     * 宿舍分页查询
     * @param dormitoryPageQueryDTO
     * @return
     */
    @Override
    public PageResult page(DormitoryPageQueryDTO dormitoryPageQueryDTO) {
        PageHelper.startPage(dormitoryPageQueryDTO.getPage(),dormitoryPageQueryDTO.getPageSize());
        Page<DormitoryVO> dormitoryVO=dormitoryMapper.selectpage(dormitoryPageQueryDTO);
        return new PageResult(dormitoryVO.getTotal(),dormitoryVO.getResult());
    }

    /**
     * 新增宿舍
     * @param dormitoryDTO
     */
    @Override
    public void saveDormitory(DormitoryDTO dormitoryDTO) {
        Building building= buildingMapper.select(dormitoryDTO.getBuildingNumber());
        //新增宿舍时判断宿舍楼是否存在
        if (building==null){
            throw new AccountNotFoundException(MessageConstant.NOT_EXIST);
        }
        //宿舍号重复错误
        Dormitory dormitory = dormitoryMapper.selectDormitory(dormitoryDTO.getDormitoryNumber());
        if(dormitory!=null){
            throw new InsufficientCapacityException("当前宿舍已存在！");
        }
        char dorNum0=dormitoryDTO.getDormitoryNumber().charAt(0);
        char buildingNumber = dormitoryDTO.getBuildingNumber().charAt(0);
        if(buildingNumber!=dorNum0){
            throw new InsufficientCapacityException("宿舍号与楼号不匹配,请重新输入！");
        }
        if(dormitoryDTO.getActualCapacity()>dormitoryDTO.getAccommodationCapacity()){
            throw new InsufficientCapacityException("已住人数不能多于可住人数");
        }
        dormitoryDTO.setBuildingId(building.getId());
        Object ActualCapacity=dormitoryDTO.getActualCapacity();
        //设置新宿舍的实际人数为0
        if(ActualCapacity==null){
            dormitoryDTO.setActualCapacity(0);
        }
        dormitoryMapper.insertdormitory(dormitoryDTO);

    }
//    @Override
//    public void saveDormitory(DormitoryDTO dormitoryDTO) {
//        //设置新宿舍的实际人数为0
//        dormitoryDTO.setActualCapacity(0);//只做新增空宿舍的功能
//        dormitoryMapper.insertdormitory(dormitoryDTO);
//
//    }

    /**
     * 根据宿舍Id查询宿舍用于修改宿舍的页面回显便于修改
     * @param id
     * @return
     */
    @Override
    public DormitoryVO getDormitory(int id) {
        Dormitory dormitory=dormitoryMapper.selectDormitoryMessage(id);
        DormitoryVO dormitoryVO = new DormitoryVO();
        BeanUtils.copyProperties(dormitory,dormitoryVO);
        return dormitoryVO;
    }

    /**
     * 修改宿舍
     * @param dormitoryDTO
     */
    @Override
    public void update(DormitoryDTO dormitoryDTO) {
        Dormitory dormitory=new Dormitory();
        log.info("dormitory:{}",dormitory);
        BeanUtils.copyProperties(dormitoryDTO,dormitory);
        //根据宿舍id查询修改前的宿舍信息
//        Dormitory dormitory1=dormitoryMapper.selectDormitoryMessage(dormitory.getId());
        log.info("dormitory1的number为{}",dormitory.getDormitoryNumber());
        Dormitory dormitory1=dormitoryMapper.getByNumber(dormitory.getDormitoryNumber());
        log.info("dormitory1:{}",dormitory1);//查询结果为空
        //修改宿舍
        dormitoryMapper.updateDormitory(dormitory);
        //查询所有学生信息用于某些学生关联了所修改的宿舍也进行修改新的宿舍号
        List<Student> studentList=studentMapper.selectAll();
        for (Student d: studentList) {
            if(dormitory != null && dormitory1 != null) {

            //使用这些学生的宿舍号和修改前的宿舍号进行对比
            if(d.getDormitoryNumber().equals(dormitory1.getDormitoryNumber())){
                //匹配成功对该学生修改新的宿舍号
                studentMapper.updateDormitoryNumber(dormitory.getDormitoryNumber());
            }
            }
            else {
                log.info("宿舍为空");
            }
        }
    }

    /**
     * 宿舍批量删除
     * @param ids
     */
    @Override
    public void deleteBatch(List<Long> ids) {
        //根据宿舍id判断这些宿舍是否有学生
            List<Long> student = studentMapper.select(ids);
            if(student!=null&&student.size()>0){
                //如果有学生不能删除
                throw new DeletionNotAllowedException(MessageConstant.There_are_students_in_the_current_dormitory_and_cannot_be_deleted);
            }else {
                //没有学生根据宿舍id集合批量删除宿舍
                dormitoryMapper.deleteByIds(ids);
            }
    }


    /**
     * 首页Echarts 查询所有宿舍号用于条形图的x轴显示
     */
    @Override
    public List<String> getDormitoryNumber() {
        List<String > dormitoryNumberList =dormitoryMapper.selectAll();
        return dormitoryNumberList;
    }
    /**
     * 主页 住宿人数
     */
    @Override
    public Long selectHaveRoomStudentNum() {
        Long dormitoryStudentNum= dormitoryMapper.selectAllDormitoryStudentNum();
        return dormitoryStudentNum;
    }
    /**
     * 首页顶部：空宿舍统计
     */
    @Override
    public int emptydormitory() {
        int actualCapacity=0;
        int emptyDormitoryNum=dormitoryMapper.selectEmptyDormitory(actualCapacity);
        return emptyDormitoryNum;
    }
}

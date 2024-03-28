package com.qgsg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qgsg.constant.MessageConstant;
import com.qgsg.dto.DormitoryDTO;
import com.qgsg.dto.DormitoryPageQueryDTO;
import com.qgsg.entity.Dormitory;
import com.qgsg.entity.Student;
import com.qgsg.exception.DeletionNotAllowedException;
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
        //设置新宿舍的实际人数为0
        dormitoryDTO.setActualCapacity(0);//只做新增空宿舍的功能
        dormitoryMapper.insertdormitory(dormitoryDTO);

    }

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
        BeanUtils.copyProperties(dormitoryDTO,dormitory);
        //根据宿舍id查询修改前的宿舍信息
        Dormitory dormitory1=dormitoryMapper.selectDormitoryMessage(dormitory.getId());
        //修改宿舍
        dormitoryMapper.updateDormitory(dormitory);
        //查询所有学生信息用于某些学生关联了所修改的宿舍也进行修改新的宿舍号
        List<Student> studentList=studentMapper.selectAll();
        for (Student d: studentList) {
            //使用这些学生的宿舍号和修改前的宿舍号进行对比
            if(d.getDormitoryNumber().equals(dormitory1.getDormitoryNumber())){
                //匹配成功对该学生修改新的宿舍号
                studentMapper.updateDormitoryNumber(dormitory.getDormitoryNumber());
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
}

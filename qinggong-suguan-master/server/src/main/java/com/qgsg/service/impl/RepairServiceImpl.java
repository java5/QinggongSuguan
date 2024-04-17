package com.qgsg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qgsg.dto.RepairDTO;
import com.qgsg.dto.RepairPageQueryDTO;
import com.qgsg.entity.Repair;
import com.qgsg.mapper.RepairMapper;
import com.qgsg.result.PageResult;
import com.qgsg.service.RepairService;
import com.qgsg.vo.RepairVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RepairServiceImpl implements RepairService {
    @Autowired
    private RepairMapper repairMapper;
    /**
     * 添加报修
     * @param repairDTO
     */
    @Override
    public void insertRepair(RepairDTO repairDTO) {
        Repair repair = new Repair();
        BeanUtils.copyProperties(repairDTO,repair);
        repair.setRepairStatus(Repair.REPAIRING);
        repair.setRepairTime(LocalDateTime.now());
        repairMapper.insert(repair);
    }

    /**
     * 报修分页查询
     * @param repairPageQueryDTO
     * @return
     */

    @Override
    public PageResult page(RepairPageQueryDTO repairPageQueryDTO) {
        PageHelper.startPage(repairPageQueryDTO.getPage(),repairPageQueryDTO.getPageSize());
        Page<RepairVO> repairVO=repairMapper.selectpage(repairPageQueryDTO);
        return new PageResult(repairVO.getTotal(),repairVO.getResult());
    }

    /**
     *  根据报修id查询报修信息用于修改报修的页面回显便于修改
     * @param id
     * @return
     */
    @Override
    public RepairVO getDormitory(int id) {
        Repair repair=repairMapper.selectRepairessage(id);
        RepairVO repairVO = new RepairVO();
        BeanUtils.copyProperties(repair,repairVO);
        return repairVO;
    }

    /**
     * 修改报修信息
     * @param repairDTO
     */
    @Override
    public void update(RepairDTO repairDTO) {

      Repair repair=new Repair();
        if(repairDTO.getRepairStatus()==2){
            repair.setCompletionTime(LocalDateTime.now());
        }
      BeanUtils.copyProperties(repairDTO,repair);
      repairMapper.update(repair);

    }

    /**
     * 报修删除
     * @param id
     */
    @Override
    public void deleteBatch(int id) {

        repairMapper.deleteByIds(id);
    }

//    /**
//     * 修改报修状态
//     * @param id
//     */
//    @Override
//    public void updateStatus(int id) {
//        Repair repair=Repair.builder()
//                .id(id)
//                .repairStatus(Repair.COMPLETED)
//                .build();
//        repairMapper.update(repair);
//    }
    /**
     * 首页顶部：报修统计
     */
    @Override
    public int showRepairNum() {
        int repairNum=repairMapper.selectRepairNum();
        return repairNum;
    }
}

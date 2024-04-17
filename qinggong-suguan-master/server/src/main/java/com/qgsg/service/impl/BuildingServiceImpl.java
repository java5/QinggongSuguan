package com.qgsg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qgsg.constant.MessageConstant;
import com.qgsg.dto.BuildingDTO;
import com.qgsg.dto.BuildingPageQueryDTO;
import com.qgsg.dto.DormitoryPageQueryDTO;
import com.qgsg.entity.Building;
import com.qgsg.entity.Dormitory;
import com.qgsg.exception.DeletionNotAllowedException;
import com.qgsg.mapper.BuildingMapper;
import com.qgsg.mapper.DormitoryMapper;
import com.qgsg.result.PageResult;
import com.qgsg.service.BuildingService;
import com.qgsg.vo.BuildingVO;
import com.qgsg.vo.DormitoryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingMapper buildingMapper;
    @Autowired
    private DormitoryMapper dormitoryMapper;
    /**
     * 宿舍楼分页查询
     * @param buildingPageQueryDTO
     * @return
     */
    @Override
    public PageResult page(BuildingPageQueryDTO buildingPageQueryDTO) {
            PageHelper.startPage(buildingPageQueryDTO.getPage(),buildingPageQueryDTO.getPageSize());
            Page<BuildingVO> buildingVO=buildingMapper.selectpage(buildingPageQueryDTO);
            return new PageResult(buildingVO.getTotal(),buildingVO.getResult());
    }
    /**
     * 新增宿舍楼
     * @param buildingDTO
     * @return
     */
    @Override
    public void saveDormitoryBuilding(BuildingDTO buildingDTO) {
        Building building=new Building();
        BeanUtils.copyProperties(buildingDTO,building);
        buildingMapper.insert(building);
    }

    /**
     * 修改宿舍楼
     * @param buildingDTO
     */
    @Override
    public void update(BuildingDTO buildingDTO) {
        Building building=new Building();
        BeanUtils.copyProperties(buildingDTO,building);
        buildingMapper.updateBuilding(building);
    }

    /**
     * 宿舍楼删除
     * @param id
     */
    @Override
    public void deleteBatch(int id) {
        //根据宿舍楼id查询这个楼有没有关联宿舍
        List<Dormitory> dormitoryList=dormitoryMapper.selectByBuildingId(id);
        //如果有，报异常不能删除
        if(dormitoryList!=null&&dormitoryList.size()>0){
            throw new DeletionNotAllowedException(MessageConstant.There_are_dor_in_the_current_building_and_cannot_be_deleted);
        }else {
            //如果没有正常删除
            buildingMapper.delete(id);
        }




    }
}

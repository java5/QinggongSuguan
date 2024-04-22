package com.qgsg.mapper;

import com.github.pagehelper.Page;
import com.qgsg.dto.RepairPageQueryDTO;
import com.qgsg.entity.Repair;
import com.qgsg.vo.RepairVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RepairMapper {

    /**
     * 添加报修
     * @param repair
     */
    void insert(Repair repair);

    /**
     * 报修分页查询
     * @param repairPageQueryDTO
     * @return
     */

    Page<RepairVO> selectpage(RepairPageQueryDTO repairPageQueryDTO);

    /**
     * 根据报修id查询报修信息用于修改报修的页面回显便于修改
     * @param id
     * @return
     */
    @Select("select * from repair where id=#{id}")
    Repair selectRepairessage(int id);

    /**
     * 修改报修
     * @param repair
     */
    void update(Repair repair);

    /**
     * 报修批量删除
     * @param ids
     */
    void deleteByIds(List<Long> ids);

    /**
     * 首页顶部：报修统计
     */
    @Select("select count(id) from repair")
    int selectRepairNum();
}

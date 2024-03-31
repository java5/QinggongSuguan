package com.qgsg.controller.admin;

import com.qgsg.dto.RepairDTO;
import com.qgsg.dto.RepairPageQueryDTO;
import com.qgsg.result.PageResult;
import com.qgsg.result.Result;
import com.qgsg.service.RepairService;
import com.qgsg.vo.RepairVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/repair")
@Slf4j
@Api(tags = "报修相关接口")
public class RepairController {

    @Autowired
    private RepairService repairService;
    /**
     * 报修分页查询
     * @param repairPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("报修分页查询")
    public Result<PageResult> page(RepairPageQueryDTO repairPageQueryDTO){
        log.info("报修分页查询：{}",repairPageQueryDTO);
        PageResult pageResult=repairService.page(repairPageQueryDTO);
        return Result.success(pageResult);
    }
    /**
     * 添加报修
     * @param repairDTO
     * @return
     */
    @PostMapping("/insert")
    @ApiOperation("添加报修")
    public Result insertRepair(@RequestBody RepairDTO repairDTO){
        log.info("添加报修：{}",repairDTO);
        repairService.insertRepair(repairDTO);
        return Result.success();
    }
    /**
     * 根据报修id查询报修信息用于修改报修的页面回显便于修改
     * @param id
     * @return
     */
    @GetMapping("/select/{id}")
    @ApiOperation("根据报修id查询报修信息用于修改报修的页面回显便于修改")
    public Result<RepairVO> getDormitory(@PathVariable int id){
        log.info("报修id:{}",id);
        RepairVO repairVO=repairService.getDormitory(id);
        return Result.success(repairVO);
    }
    /**
     * 修改报修信息
     * @param repairDTO
     * @return
     */
    @PutMapping("/update")
    @ApiOperation("修改报修信息")
    public Result update(@RequestBody RepairDTO repairDTO){
        log.info("修改报修:{}",repairDTO);
        repairService.update(repairDTO);
        return Result.success();
    }
    /**
     * 修改报修状态
     * @param id
     * @return
     */
    @PutMapping("/status/{id}")
    @ApiOperation("修改报修状态")
    public Result update(@PathVariable int id){
        log.info("修改报修状态id:{}",id);
        repairService.updateStatus(id);
        return Result.success();
    }
    /**
     * 报修批量删除
     * @param ids
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation("报修批量删除")
    public Result delete(@RequestParam List<Long> ids){
        log.info("报修批量删除：{}",ids);
        repairService.deleteBatch(ids);
        return Result.success();
    }
}

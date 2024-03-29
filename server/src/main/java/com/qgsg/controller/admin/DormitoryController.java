package com.qgsg.controller.admin;

import com.qgsg.dto.DormitoryDTO;
import com.qgsg.dto.DormitoryPageQueryDTO;
import com.qgsg.result.PageResult;
import com.qgsg.result.Result;
import com.qgsg.service.DormitoryService;
import com.qgsg.vo.DormitoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dormitory")
@Api(tags = "宿舍相关接口")
@Slf4j
public class DormitoryController {
    @Autowired
    private DormitoryService dormitoryService;

    /**
     * 宿舍分页查询
     * @param dormitoryPageQueryDTO
     * @return
     */
    @GetMapping("/Page")
    @ApiOperation("宿舍分页查询")
    public Result<PageResult> page(DormitoryPageQueryDTO dormitoryPageQueryDTO){
        log.info("宿舍分页查询：{}",dormitoryPageQueryDTO);
        PageResult pageResult=dormitoryService.page(dormitoryPageQueryDTO);
        return Result.success(pageResult);
    }
    /**
     * 新增宿舍
     * @param dormitoryDTO
     * @return
     */
    @PostMapping("/insert")
    @ApiOperation("新增宿舍")
    public Result save(@RequestBody DormitoryDTO dormitoryDTO){
        log.info("新增宿舍：{}",dormitoryDTO);
        dormitoryService.saveDormitory(dormitoryDTO);
        return Result.success();
    }
    /**
     * 根据宿舍Id查询宿舍用于修改宿舍的页面回显便于修改
     * @param id
     * @return
     */
    @GetMapping("/select/{id}")
    @ApiOperation("根据宿舍Id查询宿舍用于修改宿舍的页面回显便于修改")
    public Result<DormitoryVO> getDormitory(@PathVariable int id){
        log.info("宿舍id:{}",id);
        DormitoryVO dormitoryVO=dormitoryService.getDormitory(id);
        return Result.success(dormitoryVO);
    }
    /**
     * 修改宿舍
     * @param dormitoryDTO
     * @return
     */
    @PutMapping("/update")
    @ApiOperation("修改宿舍")
    public Result update(@RequestBody DormitoryDTO dormitoryDTO){
        log.info("修改宿舍:{}",dormitoryDTO);
        dormitoryService.update(dormitoryDTO);
        return Result.success();
    }
    /**
     * 宿舍批量删除
     * @param ids
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation("宿舍批量删除")
    public Result delete(@RequestParam List<Long> ids){
        log.info("宿舍批量删除：{}",ids);
        dormitoryService.deleteBatch(ids);
        return Result.success();
    }
}

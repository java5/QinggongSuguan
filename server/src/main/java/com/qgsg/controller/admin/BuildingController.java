package com.qgsg.controller.admin;

import com.qgsg.dto.BuildingDTO;
import com.qgsg.dto.BuildingPageQueryDTO;
import com.qgsg.dto.DormitoryDTO;
import com.qgsg.dto.DormitoryPageQueryDTO;
import com.qgsg.result.PageResult;
import com.qgsg.result.Result;
import com.qgsg.service.BuildingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/building")
@Api(tags = "宿舍楼相关接口")
@Slf4j
public class BuildingController {
    @Autowired
    private BuildingService buildingService;
    /**
     * 宿舍楼分页查询
     * @param buildingPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("宿舍楼分页查询")
    public Result<PageResult> page(BuildingPageQueryDTO buildingPageQueryDTO){
        log.info("宿舍楼分页查询：{}",buildingPageQueryDTO);
        PageResult pageResult=buildingService.page(buildingPageQueryDTO);
        return Result.success(pageResult);
    }
    /**
     * 新增宿舍楼
     * @param buildingDTO
     * @return
     */
    @PostMapping("/insert")
    @ApiOperation("新增宿舍楼")
    public Result save(@RequestBody BuildingDTO buildingDTO){
        log.info("新增宿舍楼：{}",buildingDTO);
        buildingService.saveDormitoryBuilding(buildingDTO);
        return Result.success();
    }
    /**
     * 修改宿舍楼
     * @param buildingDTO
     * @return
     */
    @PutMapping("/update")
    @ApiOperation("修改宿舍楼")
    public Result update(@RequestBody BuildingDTO buildingDTO){
        log.info("修改宿舍楼:{}",buildingDTO);
        buildingService.update(buildingDTO);
        return Result.success();
    }
    /**
     * 宿舍楼删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation("宿舍楼删除")
    public Result delete(@PathVariable int id){
        log.info("宿舍楼删除：{}",id);
        buildingService.deleteBatch(id);
        return Result.success();
    }
}

package com.qgsg.controller.admin;

import com.qgsg.dto.FangkeDTO;
import com.qgsg.dto.FangkePageQueryDTO;
import com.qgsg.entity.Fangke;
import com.qgsg.result.PageResult;
import com.qgsg.result.Result;
import com.qgsg.service.FangkeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/fangke")
@Api(tags = "访客相关接口")
@Slf4j
public class FangkeController {
    @Autowired
    private FangkeService fangkeService;
    @PostMapping("/insert")
    @ApiOperation("新增访客")
    public Result save(@RequestBody FangkeDTO fangkeDTO){
        log.info("新增访客：{}",fangkeDTO);
        fangkeService.saveFangke(fangkeDTO);
        return Result.success();
    }
    @PutMapping("/update")
    @ApiOperation("修改访客")
    public Result update(@RequestBody FangkeDTO fangkeDTO){
        log.info("修改访客{}",fangkeDTO);
        fangkeService.updateFangke(fangkeDTO);
        return Result.success();
    }
    @GetMapping("/page")
    @ApiOperation("访客分页查询")
    public Result<PageResult> page(FangkePageQueryDTO fangkePageQueryDTO){
        log.info("分页查询：{}",fangkePageQueryDTO);
        PageResult pageResult=fangkeService.page(fangkePageQueryDTO);
        return Result.success(pageResult);
    }
    @GetMapping("/select/{name}")
    @ApiOperation("根据姓名查询访客信息")
    public Result<Fangke> getByNubmer(@PathVariable String name){
        log.info("{}",name);
        Fangke fangke = fangkeService.getByName(name);
        return Result.success(fangke);
    }
    @DeleteMapping("/delete")
    @ApiOperation("删除访客")
    public Result delete(@RequestParam List<String> names){
        log.info("删除访客");
        fangkeService.deleteFangke(names);
        return Result.success();
    }
}

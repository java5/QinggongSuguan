package com.qgsg.controller.admin;

import com.github.pagehelper.Page;
import com.qgsg.dto.SignDTO;
import com.qgsg.entity.Sign;
import com.qgsg.result.PageResult;
import com.qgsg.result.Result;
import com.qgsg.service.SignService;
import com.qgsg.vo.SignVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/sign")
@Api(tags = "签到表")
@Slf4j
public class SignController {
    @Autowired
    private SignService signService;

    /**
     * 签到分页查询
     * @param signDTO
     * @return
     */
    @GetMapping("/select")
    @ApiOperation("签到表分页查询")
    public Result<PageResult> page(SignDTO signDTO){
        log.info("签到表分页查询：{}",signDTO);
        PageResult pageResult=signService.page(signDTO);
        return Result.success(pageResult);
    }

    /**
     * 根据学号查询签到表信息
     *
     * @param number
     * @return
     */
    @GetMapping("/select/{number}")
    @ApiOperation("根据学号查询学生信息")
    public Result<Page<SignVO>> getByNubmer(@PathVariable String number){
        log.info("{}",number);
        Page<SignVO> sign = signService.getByNumber(number);
        return Result.success(sign);
    }

    /**
     * 根据学号批量删除签到表
     * @param numbers
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation("删除签到表")
    public Result delete(@RequestParam List<String> numbers){
        log.info("删除签到表");
        signService.deleteSign(numbers);
        return Result.success();
    }

    @PutMapping("/update")
    @ApiOperation("修改签到表")
    public Result update(@RequestBody SignDTO signDTO){
        log.info("修改学生{}",signDTO);
        signService.updateSign(signDTO);
        return Result.success();
    }

    @GetMapping("select/dor/{dorNumber}")
    @ApiOperation("根据宿舍号查询签到学生")
    public Result<List<Sign>> getByDronumber(@PathVariable String dorNumber){
        log.info("接收的dornumber{}",dorNumber);
        List<Sign> sign=signService.getByDornumber(dorNumber);
        return Result.success(sign);
    }
}

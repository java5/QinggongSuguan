package com.qgsg.controller.admin;

import com.github.pagehelper.Page;
import com.qgsg.dto.SignDTO;
import com.qgsg.dto.SignPageQueryDTO;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public Result<PageResult> page1(SignDTO signDTO){
        log.info("签到表分页查询：{}",signDTO);
        PageResult pageResult=signService.page1(signDTO);
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
        signService.deleteSign1(numbers);
        return Result.success();
    }

    /**
     * 改签到表
     * @param signDTO
     * @return
     */
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

    /**
     * 导出excel签到数据表
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping("/export")
    public Result export(HttpServletResponse response) throws IOException {
        signService.getexprot( response);
        return Result.success();
    }




    /**
     * 签到分页查询
     * @param signPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("签到表分页查询")
    public Result<PageResult> page(SignPageQueryDTO signPageQueryDTO){
        log.info("签到表分页查询：{}",signPageQueryDTO);
        PageResult pageResult=signService.page(signPageQueryDTO);
        return Result.success(pageResult);
    }
    /**
     * 根据id删除签到表
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除签到表")
    public Result delete(@PathVariable int id){
        log.info("删除签到表id:{}",id);
        signService.deleteSign(id);
        return Result.success();
    }
}

package com.qgsg.controller.admin;

import com.qgsg.dto.SignDTO;
import com.qgsg.dto.StudentPageQueryDTO;
import com.qgsg.result.PageResult;
import com.qgsg.result.Result;
import com.qgsg.service.SignService;
import com.qgsg.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

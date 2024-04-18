package com.qgsg.controller.admin;

import com.qgsg.dto.TeacherDTO;
import com.qgsg.result.Result;
import com.qgsg.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 教师管理
 */
@RestController
@RequestMapping("/admin/teacher")
@Api(tags = "注册接口")
@Slf4j
public class ZhuceController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 新增管理员
     * @param teacherDTO
     * @return
     */
    @PostMapping("/insert")
    @ApiOperation("注册管理员")
    public Result save(@RequestBody TeacherDTO teacherDTO){
        String password=teacherDTO.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        teacherDTO.setPassword(password);
        log.info("注册管理员:{}",teacherDTO);
        teacherService.save(teacherDTO);
        return Result.success();
    }

}

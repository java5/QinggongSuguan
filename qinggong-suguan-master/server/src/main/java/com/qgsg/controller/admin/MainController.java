package com.qgsg.controller.admin;

import com.qgsg.context.BaseContext;
import com.qgsg.dto.DormitoryDTO;
import com.qgsg.dto.TeacherDTO;
import com.qgsg.entity.Teacher;
import com.qgsg.result.Result;
import com.qgsg.service.MainService;
import com.qgsg.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/main")
@Slf4j
@Api(tags = "个人信息相关接口")
public class MainController {
    @Autowired
    private MainService mainService;
    @Autowired
    private TeacherService teacherService;

    /**
     * 获取个人信息
     * @return
     */
    @GetMapping("/message")
    public Result<Teacher> getMain(){
        log.info("个人信息");
        Long id = BaseContext.getCurrentId();
        log.info("id:{}",id);
        Teacher teacher=  mainService.getMessage(id);
     return Result.success(teacher);
    }
//    /**
//     * 修改个人信息
//     * @param teacherDTO
//     * @return
//             */
//    @PutMapping("/update")
//    @ApiOperation("修改个人信息")
//    public Result update(@RequestBody TeacherDTO teacherDTO){
//        log.info("修改个人信息:{}",teacherDTO);
//        teacherService.updateTeacher(teacherDTO);
//        return Result.success();
//    }
}

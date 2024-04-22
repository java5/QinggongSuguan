package com.qgsg.controller.admin;

import com.qgsg.context.BaseContext;
import com.qgsg.entity.Teacher;
import com.qgsg.result.Result;
import com.qgsg.service.MainService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/main")
@Slf4j
@Api(tags = "个人信息相关接口")
public class MainController {
    @Autowired
    private MainService mainService;
//    @Autowired
//    private TeacherService teacherService;

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

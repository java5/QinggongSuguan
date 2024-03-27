package com.qgsg.controller.admin;

import com.qgsg.dto.StudentDTO;
import com.qgsg.result.Result;
import com.qgsg.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/student")
@Api(tags = "学生相关接口")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 新增学生
     * @param studentDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增学生")
    public Result save(@RequestBody StudentDTO studentDTO){
        log.info("新增学生：{}",studentDTO);
        studentService.saveStudent(studentDTO);
        return Result.success();
    }

}

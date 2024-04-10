package com.qgsg.controller.admin;

import com.qgsg.dto.StudentDTO;
import com.qgsg.dto.StudentPageQueryDTO;
import com.qgsg.entity.Student;
import com.qgsg.result.PageResult;
import com.qgsg.result.Result;
import com.qgsg.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/inseat")
    @ApiOperation("新增学生")
    public Result save(@RequestBody StudentDTO studentDTO){
        log.info("新增学生：{}",studentDTO);
        studentService.saveStudent(studentDTO);
        return Result.success();
    }


    /**
     * 修改学生
     * @param studentDTO
     * @return
     */
    @PutMapping("/update")
    @ApiOperation("修改学生")
    public Result update(@RequestBody StudentDTO studentDTO){
        log.info("修改学生{}",studentDTO);
        studentService.updateStudent(studentDTO);
        return Result.success();
    }


    /**
     * 学生分页查询
     * @param studentPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("学生分页查询")
    public Result<PageResult> page(StudentPageQueryDTO studentPageQueryDTO){
        log.info("分页查询：{}",studentPageQueryDTO);
        PageResult pageResult=studentService.page(studentPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 根据学号查询学生信息
     * @param number
     * @return
     */
    @GetMapping("/select/{number}")
    @ApiOperation("根据学号查询学生信息")
    public Result<Student> getByNubmer(@PathVariable String number){
        log.info("{}",number);
        Student student = studentService.getByNumber(String.valueOf(number));
        return Result.success(student);
    }



    /**
     * 根据学号开除学生，可批量
     * @param numbers
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation("开除学生")
    public Result delete(@RequestParam List<String> numbers){
        log.info("删除学生");
        studentService.deleteStudent(numbers);
        return Result.success();
    }

    @GetMapping("select/dor/{dorNumber}")
    public Result<List<Student>> getByDronumber(@PathVariable String dorNumber){
        log.info("接收的dornumber{}",dorNumber);
        List<Student> student=studentService.getByDornumber(dorNumber);
        return Result.success(student);
    }
}

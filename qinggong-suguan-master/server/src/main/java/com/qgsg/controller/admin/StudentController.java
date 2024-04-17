package com.qgsg.controller.admin;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.stream.CollectorUtil;
import cn.hutool.core.util.ObjectUtil;
import com.qgsg.context.BaseContext;
import com.qgsg.dto.StudentDTO;
import com.qgsg.dto.StudentPageQueryDTO;
import com.qgsg.entity.Student;
import com.qgsg.mapper.StudentMapper;
import com.qgsg.result.PageResult;
import com.qgsg.result.Result;
import com.qgsg.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/student")
@Api(tags = "学生相关接口")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMapper studentMapper;

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
     * 首页echarts（饼图）查询各宿舍学生签到人数
     * @param
     * @return
     */
    @GetMapping("/echarts/selectSignNum")
    @ApiOperation("首页echarts 查询各宿舍学生签到人数")
    public Result getNubmer(){
        List<Student> studentlist = studentService.getStudtentAll();
        Map<String, Long> map = studentlist.stream()
                .filter(x -> ObjectUtil.isNotEmpty(x.getDormitoryNumber()))
                .collect(Collectors.groupingBy(Student::getDormitoryNumber, Collectors.counting()));
        List<Map<String,Object>> mapList=new ArrayList<>();
        if(CollectionUtil.isNotEmpty(map)){
            for (String key : map.keySet()) {
                Map<String,Object> map1 =new HashMap<>();
                map1.put("name",key);
                map1.put("value",map.get(key));
                mapList.add(map1);
            }
        }
        return Result.success(mapList);
    }

    /**
     * 开除学生
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation("开除学生")
    public Result delete(@PathVariable int id){
        log.info("删除学生");
        studentService.deleteStudent(id);
        return Result.success();
    }
    /**
     * 首页echarts（条图）-根据宿舍集合查询个宿舍人数
     */
    @GetMapping("/getEachBuildingStuNum/{dormitoryNumbers}")
    public Result<List<Long>> getEachBuildingStuNum(@PathVariable List<String> dormitoryNumbers) {
        ArrayList<Long> arrayList = new ArrayList();
        for (String dNs : dormitoryNumbers) {
            Long studentNum=studentMapper.selectByDormitoryNumber(dNs);
            arrayList.add(studentNum);
        }
            return Result.success(arrayList);
    }
    /**
     * 首页echarts（双条图）-各宿舍签到人数
     */
    @GetMapping("/getSignStuNum/{dormitoryNumbers}")
    public Result<List<Long>> getDormitorySignStuNum(@PathVariable List<String> dormitoryNumbers) {
        log.info("宿舍签到人数");
        ArrayList<Long> arrayList = new ArrayList();
        for (String dNs : dormitoryNumbers) {
            Long studentSignNum=studentMapper.selectSignNumber(dNs);
            arrayList.add(studentSignNum);
        }
        return Result.success(arrayList);
    }
    /**
     * 首页echarts（双条图）-宿舍未签到人数
     */
    @GetMapping("/getNotSignStuNum/{dormitoryNumbers}")
    public Result<List<Long>> getDormitoryNotSignStuNum(@PathVariable List<String> dormitoryNumbers) {
        log.info("宿舍未签人数");
        ArrayList<Long> arrayList = new ArrayList();
        for (String dNs : dormitoryNumbers) {
            Long studentNotSignNum=studentMapper.selectNotSignNumber(dNs);
            arrayList.add(studentNotSignNum);
        }
        return Result.success(arrayList);
    }
    /**
     * 首页echarts（饼）-所有学生签到/未签到人数
     */
    @GetMapping("/getAllStuNum")
    public Result getDormitorySignStuNum() {
        log.info("所有学生签到/未签人数");
        Long AllSignNum=studentMapper.selectAllSignNumber();
        Long AllNotSignNum=studentMapper.selectAlllNotSignNumber();
        List<Map<String,Object>> mapList=new ArrayList<>();
        Map<String,Object> map1 =new HashMap<>();
                map1.put("name","已签到");
                map1.put("value",AllSignNum);
                mapList.add(map1);
        Map<String,Object> map2 =new HashMap<>();
                map2.put("name","未签到");
                map2.put("value",AllNotSignNum);
                mapList.add(map2);
        return Result.success(mapList);
    }
    /**
     * 主页顶部：学生统计
     */
    @GetMapping("/studentNum")
    public Result<Integer> stuNum() {
        int num = studentService.studentNum();
        return Result.success(num);

    }
}

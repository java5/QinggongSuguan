package com.qgsg.task;

import com.qgsg.entity.Student;
import com.qgsg.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 自定义定时任务类
 */
@Component
@Slf4j
public class OrderTask {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 处理学生签到状态，每天改回未签到状态（0）
     */
    //@Scheduled(cron = "1 * * * * ? ")//每分钟触发一次
    @Scheduled(cron = "0 0 1 * * ? ")//每天凌晨一点触发
    public void processTimeOutOrder(){
        log.info("定时处理学生签到状态：{}", LocalDateTime.now());
        //签到状态
        int signStatus = 1;
        List<Student> studentList=studentMapper.getBystatusSign(signStatus);
        if(studentList!=null&&studentList.size()>0){
            for (Student student : studentList) {
                student.setSignStatus(0);
                studentMapper.update(student);

            }
        }
    }
}

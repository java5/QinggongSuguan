package com.qgsg.controller.admin;

import com.qgsg.config.mqtt.MqttInboundConfiguration;
import com.qgsg.constant.JwtClaimsConstant;
import com.qgsg.dto.TeacherLoginDTO;
import com.qgsg.entity.Teacher;
import com.qgsg.properties.JwtProperties;
import com.qgsg.result.Result;
import com.qgsg.service.TeacherService;
import com.qgsg.utils.JwtUtil;
import com.qgsg.vo.TeacherLoginVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 教师管理
 */
@RestController
@RequestMapping("/admin/teacher")
@Api(tags = "管理员老师相关接口")
@Slf4j
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private MqttInboundConfiguration mqttInboundConfiguration;

    /**
     * 教师登录
     *
     * @param teacherLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<TeacherLoginVO> login(@RequestBody TeacherLoginDTO teacherLoginDTO) {


        //mqtt test
        log.info("mqtt测试开始");
        String lastMessage = mqttInboundConfiguration.getLastReceivedMessage();
        System.out.println("Controller层获取到的最后接收到的消息: " + lastMessage);
        log.info("mqtt测试结束");


        log.info("教师登录：{}", teacherLoginDTO);

        Teacher teacher = teacherService.login(teacherLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, teacher.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        TeacherLoginVO teacherLoginVO = TeacherLoginVO.builder()
                .id(teacher.getId())
                .userName(teacher.getUsername())
                .name(teacher.getName())
                .token(token)
                .build();

        return Result.success(teacherLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

}

package com.qgsg.controller.admin;

import cn.hutool.json.JSONUtil;
import com.qgsg.common.api.CommonResult;
import com.qgsg.dto.MqttCmds;
import com.qgsg.service.MqttGateWayService;
import com.qgsg.service.MqttMessageRetrieverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
public class MqttController {
    @Autowired
    private MqttMessageRetrieverService mqttMessageRetrieverService;
    @PostConstruct
    public void someMethod() {
        System.out.println("controller运行中");
        mqttMessageRetrieverService.retrieveAndOutputLastMessage();
    }
}

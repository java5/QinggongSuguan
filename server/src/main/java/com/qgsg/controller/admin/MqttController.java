package com.qgsg.controller.admin;

import cn.hutool.json.JSONUtil;
import com.qgsg.common.api.CommonResult;
import com.qgsg.dto.MqttCmds;
import com.qgsg.service.MqttGateWayService;
import com.qgsg.common.api.CommonResult;
import com.qgsg.service.MqttGateWayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Create
 */
@Controller
@RequestMapping(value = "/mqtt")
@Api(value = "MqttController", tags = {"MQTT 访问控制"})
public class MqttController {

    @Autowired
    private MqttGateWayService gateWay;


    @RequestMapping(value = "/sendMqtt", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult sendMqtt(@RequestParam(value = "topic", required = true) @ApiParam(value = "MQTT的主题") String topic, @RequestParam(value = "deviceId") @ApiParam(value = "设备唯一标识符") String deviceId, @RequestParam(value = "sensorTag") @ApiParam(value = "传感器标识") String sensorTag, @RequestBody @ApiParam(value = "控制命令,由 key,value组成") MqttCmds cmd) {
        try {
            String detailTopic = topic + "/deviceId/" + deviceId + "/sensorTag/" + sensorTag;
            System.out.println("-> " + detailTopic + " -> " + cmd.toString());
            gateWay.sendMessageToMqtt(JSONUtil.toJsonStr(cmd), detailTopic);

            return CommonResult.success(cmd);
        } catch (Exception e) {

        }
        return CommonResult.failed();
    }


    @RequestMapping(value = "/sendMqttSimple", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult sendMqttSimple(@RequestParam(value = "topic", required = true) @ApiParam(value = "MQTT的主题") String topic, String data) {
        try {
            gateWay.sendMessageToMqtt(data, topic);
            return CommonResult.success(data);
        } catch (Exception e) {

        }
        return CommonResult.failed();
    }

}

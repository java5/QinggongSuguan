package com.qgsg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qgsg.domain.IOTSensor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Spring
 * @description tb_sensor
 * @Entity com.hua.iot.domain.IOTSensor
 */
@Mapper
public interface IOTSensorMapper extends BaseMapper<IOTSensor> {
}





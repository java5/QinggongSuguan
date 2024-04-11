package com.qgsg.service.impl;

import com.qgsg.mapper.SignMapper;
import com.qgsg.service.ReportService;
import com.qgsg.vo.SignReportVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Autowired
    private SignMapper signMapper;

    /**
     * 统计指定时间区间内的签到信息
     * @param begin
     * @param end
     * @return
     */
    public SignReportVO getSignStatistics(LocalDate begin, LocalDate end) {

        //当前集合用于存放从begin到end范围内的每天的日期
        List<LocalDate> dateList = new ArrayList<>();

        dateList.add(begin);

        while (!begin.equals(end)) {
            //一直遍历到最后一天
            begin = begin.plusDays(1);//plusDays:加1天
            dateList.add(begin);
        }//把每天的日期加入集合


        //存放每天的数据
        List<Integer> signList = new ArrayList<>();
        for (LocalDate date : dateList) {
            log.info("当天的date为{}",date);
            //查询date日期对应的签到数据
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);
            log.info("转换的当天date：begin:{},   end:{}",beginTime,endTime);

            //select sum(sign_status) from sign where sign_time > '2024-04-07 00:00' and sign_time < '2024-04-10 23:59:59.999999999'
            Integer sign = signMapper.sumByMap(beginTime,endTime);//当天签到成功的总和
            log.info("sign为{}",sign);
            sign = sign == null ? 0 : sign;
            signList.add(sign);
        }

        //封装返回结果
        return SignReportVO
                .builder()
                .dateList(StringUtils.join(dateList, ","))
                .signList(StringUtils.join(signList, ","))
                .build();
    }

}

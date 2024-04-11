package com.qgsg.service;

import com.qgsg.vo.SignReportVO;

import java.time.LocalDate;


public interface ReportService {
    /**
     * 统计指定时间范围的签到统计信息
     * @param begin
     * @param end
     * @return
     */
    SignReportVO getSignStatistics(LocalDate begin, LocalDate end);
}

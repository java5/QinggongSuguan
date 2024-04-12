package com.qgsg.controller.admin;

import com.qgsg.result.Result;
import com.qgsg.service.ReportService;
import com.qgsg.vo.SignReportVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * 签到统计报表
 */
@RestController
@RequestMapping("/admin/report")
@Api(tags = "签到统计报表接口")
@Slf4j
public class ReportController {
    @Autowired
    private ReportService reportService;


    /**
     * 签到统计
     * @return
     */
    @GetMapping("/signStatistics")
    @ApiOperation("签到统计")
    public Result<SignReportVO> signStatistics() {
        LocalDate begin = LocalDate.now();
        LocalDate end = LocalDate.now();

        log.info("签到数据统计：{},{}", begin, end);
        return Result.success(reportService.getSignStatistics(begin, end));
    }
}

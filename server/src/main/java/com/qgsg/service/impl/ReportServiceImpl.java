package com.qgsg.service.impl;

import com.qgsg.mapper.SignMapper;
import com.qgsg.service.ReportService;
import com.qgsg.service.SignService;
import com.qgsg.vo.SignReportVO;
import com.qgsg.vo.SignVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
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
    @Autowired
    private SignService signService;

    /**
     * 统计指定时间区间内的签到信息
     *
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
            log.info("当天的date为{}", date);
            //查询date日期对应的签到数据
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);
            log.info("转换的当天date：begin:{},   end:{}", beginTime, endTime);

            //select sum(sign_status) from sign where sign_time > '2024-04-07 00:00' and sign_time < '2024-04-10 23:59:59.999999999'
            Integer sign = signMapper.sumByMap(beginTime, endTime);//当天签到成功的总和
            log.info("sign为{}", sign);
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


    /**
     * 导出签到表
     * @param response
     */
    public void exportSign(HttpServletResponse response) {
        //1. 查询数据库，获取签到数据---查询最近30天的数据
        LocalDate dateBegin = LocalDate.now().minusDays(30);//往前数30天
        LocalDate dateEnd = LocalDate.now().minusDays(1);//查到前一天，今天未结束

        LocalDateTime begin = LocalDateTime.of(dateBegin, LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(dateEnd, LocalTime.MAX);


        //2. 通过POI将数据写入到Excel文件中
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("template/签到表.xlsx");//获取输入流对象

        List<SignVO> signs =signMapper.select(begin,end);
        log.info("sign:{}",signs);


        try {
            //基于模板文件创建一个新的Excel文件
            XSSFWorkbook excel = new XSSFWorkbook(in);

            //获取表格文件的Sheet页
            XSSFSheet sheet = excel.getSheet("Sheet1");

            //填充数据--时间
            sheet.getRow(1).getCell(1).setCellValue("时间：" + dateBegin + "至" + dateEnd);


            int j=0;
            //填充签到表数据
            for (SignVO sign : signs) {
                int id = sign.getId();
                String number = sign.getNumber();
                String name = sign.getName();
                int signStatus = sign.getSignStatus();
                LocalDateTime signTime = sign.getSignTime();
                String dormitoryNumber = sign.getDormitoryNumber();
                log.info("{},{},{},{},{},{}",id,number,name,signStatus,signTime,dormitoryNumber);

                XSSFRow row = sheet.getRow(3);
                //获得某一行
                row = sheet.getRow(7+j++);
                row.getCell(1).setCellValue(id);
                row.getCell(2).setCellValue(number);
                row.getCell(3).setCellValue(name);
                row.getCell(4).setCellValue(signStatus);
                row.getCell(5).setCellValue(signTime.toString());
                row.getCell(6).setCellValue(dormitoryNumber);
            }

            //3. 通过输出流将Excel文件下载到客户端浏览器
            ServletOutputStream out = response.getOutputStream();
            excel.write(out);

            //关闭资源
            out.close();
            excel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

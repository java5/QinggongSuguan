package com.qgsg.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignReportVO implements Serializable {

    //日期，以逗号分隔
    private String dateList;

    //签到信息，以逗号分隔
    private String signList;

}

package com.qgsg.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "签到表返回的数据格式")
public class SignVO implements Serializable {

    private int id;

    private String number;

    private String name;

    private String signStatus;

    private LocalDateTime signTime;

    private String dormitoryNumber;

}

package com.qgsg.entity;

import com.alibaba.druid.stat.TableStat;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import lombok.*;

import java.time.LocalDateTime;

import static com.alibaba.druid.stat.TableStat.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Student {
    private String id;

    private String number;

    private String name;

    private String sex;

    private int age;

    private String phone;

    private String dormitoryNumber;

    private String buildingNumber;


    private String fingerPrint;

    private int signStatus;

    private LocalDateTime signTime;

}

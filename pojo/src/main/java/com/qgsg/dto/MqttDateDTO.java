package com.qgsg.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MqttDateDTO {

    private String message;

    private LocalDate today_date;



}

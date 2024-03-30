package com.qgsg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@Slf4j
@EnableScheduling
public class QGSGApplication {
    public static void main(String[] args) {
        SpringApplication.run(QGSGApplication.class, args);
        log.info("server started");
    }
}

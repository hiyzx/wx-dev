package org.zero.miniprogram.appointment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "org.zero.miniprogram.appointment.dao")
public class MiniprogramAppointmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniprogramAppointmentApplication.class, args);
    }
}

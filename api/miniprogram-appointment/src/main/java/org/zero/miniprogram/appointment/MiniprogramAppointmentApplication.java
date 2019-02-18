package org.zero.miniprogram.appointment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.zero.miniprogram.appointment")
public class MiniprogramAppointmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniprogramAppointmentApplication.class, args);
    }
}

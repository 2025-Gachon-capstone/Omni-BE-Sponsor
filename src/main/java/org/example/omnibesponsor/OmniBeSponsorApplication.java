package org.example.omnibesponsor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients(basePackages = "org.example.omnibesponsor.client")
public class OmniBeSponsorApplication {

    public static void main(String[] args) {
        SpringApplication.run(OmniBeSponsorApplication.class, args);
    }

}

package org.example.omnibesponsor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OmniBeSponsorApplication {

    public static void main(String[] args) {
        SpringApplication.run(OmniBeSponsorApplication.class, args);
    }

}

package com.ogray.djs.node;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DjsApplication {
    public static String classPoolPath = "C:\\work\\github\\djs\\node\\classpool\\";
    public static void main(String[] args) {
        SpringApplication.run(DjsApplication.class, args);
    }

}

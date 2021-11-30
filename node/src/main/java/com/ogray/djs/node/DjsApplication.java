package com.ogray.djs.node;

import com.ogray.djs.pool.TaskPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DjsApplication {
    public static String classPoolPath = "C:\\work\\github\\djs\\node\\classpool\\";
    public static TaskPool taskPool = new TaskPool();
    //public RestManager

    public static void main(String[] args) {
        SpringApplication.run(DjsApplication.class, args);
    }

}

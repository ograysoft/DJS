package com.ogray.djs.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.codec.binary.Base64;

public class AddTaskRequest {
    @Setter @Getter
    String className;

    @Setter @Getter
    String data;

    public AddTaskRequest(String className, byte[] data) {
        this.className = className;
        this.data = Base64.encodeBase64String(data);
    }
}

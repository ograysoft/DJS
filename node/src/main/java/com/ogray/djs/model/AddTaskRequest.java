package com.ogray.djs.model;

import lombok.Getter;
import lombok.Setter;

public class AddTaskRequest {
    @Setter @Getter
    String className;

    @Setter @Getter
    String data;
}

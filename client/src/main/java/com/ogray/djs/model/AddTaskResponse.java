package com.ogray.djs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class AddTaskResponse {
    @Setter @Getter
    int status;

    @Setter @Getter
    String data;
}

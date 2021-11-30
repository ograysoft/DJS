package com.ogray.djs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class AddAsyncTaskResponse {
    @Setter @Getter
    Integer status;

    @Setter @Getter
    Long taskId;
}

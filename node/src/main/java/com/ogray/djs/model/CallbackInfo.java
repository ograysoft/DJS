package com.ogray.djs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Callback sent to caller about task progress
 */
@AllArgsConstructor
public class CallbackInfo {
    public static final int TASK_COMPLETED = 1;

    @Setter @Getter
    String node;

    @Setter @Getter
    Long taskId;

    @Setter @Getter
    Integer status;

}

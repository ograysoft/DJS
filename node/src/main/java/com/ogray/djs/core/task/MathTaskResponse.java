package com.ogray.djs.core.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
public class MathTaskResponse implements Serializable {
    @Setter @Getter
    Integer result;

    public String toString() {
        return "[" + result + "]";
    }
}

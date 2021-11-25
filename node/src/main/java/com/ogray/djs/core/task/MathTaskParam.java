package com.ogray.djs.core.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
public class MathTaskParam implements Serializable {
    @Setter @Getter
    Integer a;
    @Setter @Getter
    Integer b;
}

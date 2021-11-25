package com.ogray.djs.core.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Example remote task which multiply two integers
 */
@AllArgsConstructor
public class Math2Task extends DjsTask {
    @Setter
    @Getter
    Integer a;

    @Setter @Getter
    Integer b;

    @Override
    public Object execute() {
        MathTaskParam param = new MathTaskParam(a, b);
        MathTaskResponse resp = new MathTaskResponse(param.getA() * param.getB());
        return resp;
    }


}

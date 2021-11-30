package com.ogray.djs.pool;


import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;

public class RealNodeTask implements NodeTask, Runnable {
    private Object task;

    @Setter @Getter
    Long taskId;

    public RealNodeTask(Object obj) {
        this.task = obj;
    }

    @Override
    public int getCompleteLevel() {
        return 0;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public void run() {
        Method method1;
        try {
            method1 = task.getClass().getMethod("execute", null);
            Object res = method1.invoke(task, null);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

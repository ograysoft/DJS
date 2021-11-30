package com.ogray.djs.pool;

import java.lang.reflect.Method;

public class Job {
    public void doRequest(Object task, final CallBack callBack){
        new Thread(() -> {
            //Async Long running task
            try {
            //    Thread.sleep(10000);
                Method method1 = task.getClass().getMethod("execute", null);
                Object res = method1.invoke(task, null);

                callBack.onRequestComplete("job done: "+ res);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }).start();
    }
}

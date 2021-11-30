package com.ogray.djs.pool;

import com.ogray.djs.core.rest.RestManager;
import com.ogray.djs.model.CallbackInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Hold pool of running tasks
 */
@Slf4j
public class TaskPool {
    //ExecutorService pool = Executors.newFixedThreadPool(n);

    private HashMap<Long, NodeTask> pool = new HashMap<Long, NodeTask>();
    private Long counter = Long.valueOf(0L);

    /**
     * Adds new task to pool
     * @param task
     * @return
     */
    public synchronized Long addTask(RealNodeTask task) {
        counter++;
        Long newTaskId = counter;

        log.info("adding new task. taskId="+newTaskId);
        task.setTaskId(newTaskId);
        pool.put(newTaskId, task);

        task.run();

        return newTaskId;
    }

    /**
     * Get complete level of one task in pool
     * @param taskId
     * @return
     * @throws NoSuchElementException
     */
    public int getTaskCompleteLevel(Long taskId) throws NoSuchElementException {
        NodeTask task = pool.get(taskId);
        if(task==null)
            throw new NoSuchElementException();

        return task.getCompleteLevel();
    }

    public void executeJob(Object task, Long taskId, String callback) {
            Job job = new Job();
            job.doRequest(task, new CallBack() {
                @Override
                public void onRequestComplete(String response) {
                    System.out.println("onRequestComplete :" + response);

                    try {
                        RestManager.sendCallback(callback, taskId, CallbackInfo.TASK_COMPLETED);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }

}

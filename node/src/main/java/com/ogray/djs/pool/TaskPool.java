package com.ogray.djs.pool;

/**
 * 	Copyright (C) 2021 Sergey A. Salata. All rights reserved.
 *
 *	This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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

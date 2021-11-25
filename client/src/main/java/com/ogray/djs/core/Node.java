package com.ogray.djs.core;

import com.ogray.djs.core.rest.RestManager;
import com.ogray.djs.core.task.DjsTask;
import com.ogray.djs.model.AddClassResponse;
import com.ogray.djs.model.AddTaskResponse;
import lombok.Getter;
import lombok.Setter;

public class Node {
    /**
     * Represent DJS node
     */

    @Setter @Getter
    String ip;

    public Node(String ip) {
        this.ip = ip;
    }

    /**
     * Upload task to remote node
     * @param task
     */
    public int addTask(DjsTask task) throws Exception {
        byte[] byteTask = task.serialize();

        AddTaskResponse response = RestManager.addTask(this.ip, byteTask, task.getClass().getCanonicalName());
        return response.getStatus();
    }

    /**
     * Upload task to remote node
     * @param name - full class name with .class
     */
    public int addClass(String name, byte[] bin) throws Exception {
        AddClassResponse response = RestManager.addClass(this.ip, name, bin);
        return response.getStatus();
    }
}

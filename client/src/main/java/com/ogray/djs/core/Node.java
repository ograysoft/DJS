package com.ogray.djs.core;

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
import com.ogray.djs.core.task.DjsTask;
import com.ogray.djs.model.AddAsyncTaskResponse;
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
    public int execTaskSync(DjsTask task) throws Exception {
        byte[] byteTask = task.serialize();

        AddTaskResponse response = RestManager.execTaskSync(this.ip, byteTask, task.getClass().getCanonicalName());
        return response.getStatus();
    }

    /**
     * Upload task to remote node
     * @param task
     */
    public int execTaskAsync(DjsTask task) throws Exception {
        byte[] byteTask = task.serialize();

        AddAsyncTaskResponse response = RestManager.execTaskAsync(this.ip,
                byteTask, task.getClass().getCanonicalName());
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

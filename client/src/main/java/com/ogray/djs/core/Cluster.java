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

import com.ogray.djs.core.task.DjsTask;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Cluster {
    /**
     * Set of Node is Cluster
     */

    Set<Node> nodes = new HashSet<Node>();
    public Cluster() {
    }

    /**
     * Add node to cluster
     * @param ip
     */
    public void addNode(String ip) {
        nodes.add(new Node(ip));
    }

    public HashMap<String,Integer> addClass(String name, byte[] bin) throws Exception {
        HashMap<String,Integer> result = new HashMap<String,Integer>();
        for(Node node : nodes) {
            int response = node.addClass(name, bin);
            result.put(node.ip, response);
        }
        return result;
    }

    /**
     * Upload task to all nodes in cluster
     * @param task
     */
    public HashMap<String,Integer> execTaskSync(DjsTask task) throws Exception {
        HashMap<String,Integer> result = new HashMap<String,Integer>();
        for(Node node : nodes) {
            int response = node.execTaskSync(task);
            result.put(node.ip, response);
        }
        return result;
    }

    /**
     * Upload async task to all nodes in cluster
     * @param task
     */
    public HashMap<String,Integer> execTaskAsync(DjsTask task) throws Exception {
        HashMap<String,Integer> result = new HashMap<String,Integer>();
        for(Node node : nodes) {
            int response = node.execTaskAsync(task);
            result.put(node.ip, response);
        }
        return result;
    }
}

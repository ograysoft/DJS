package com.ogray.djs.core;

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

package com.ogray.djs.app;


import com.ogray.djs.core.Cluster;
import com.ogray.djs.core.Utils;
import com.ogray.djs.core.task.DjsTask;
import com.ogray.djs.core.task.Math2Task;
import com.ogray.djs.core.task.MathTaskParam;
import com.ogray.djs.core.task.MathTaskResponse;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.HashMap;

@Slf4j
public class DjsClientApplication {

    /**
     * Adds single class to cluster, uplaods binary of class to all nodes
     * @param cluster
     * @param cls
     * @throws Exception
     */
    static void addClassToCluster(Cluster cluster, Class cls) throws Exception {
        String className = cls.getCanonicalName();
        className = className.replace('.', '/') + ".class";

        ClassLoader loader = cls.getClassLoader();
        URL classFileName = loader.getResource(className);//"com/ogray/djs/core/task/DjsTask.class");
        String classFileNamePath = classFileName.getPath().substring(1);
        System.out.println("classFileName= " + classFileNamePath);
        byte[] classBin = Utils.loadFile(classFileNamePath);
        cluster.addClass(cls.getCanonicalName() + ".class", classBin);
    }

    public static void main(String[] args) {
        Cluster cluster = new Cluster();
        cluster.addNode("http://localhost:8383");
        // add other nodes here
        // cluster.addNode("http://ip2:8383");

        try {
            // upload Task class, superclass and arguments classes to cluster
            addClassToCluster(cluster, DjsTask.class);
            addClassToCluster(cluster, Math2Task.class);
            addClassToCluster(cluster, MathTaskParam.class);
            addClassToCluster(cluster, MathTaskResponse.class);

            // executes remotely task
            HashMap<String,Integer> result = cluster.execTaskAsync(new Math2Task(1,2));
            for(String ip : result.keySet()) {
                System.out.println("Result from ["+ip+"] is "+result.get(ip));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

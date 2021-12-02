package com.ogray.djs.app;

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

import com.ogray.djs.core.Cluster;
import com.ogray.djs.core.Utils;
import com.ogray.djs.core.task.Math2Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URL;
import java.util.HashMap;

@Slf4j
@SpringBootApplication
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
        URL classFileName = loader.getResource(className);
        String classFileNamePath = classFileName.getPath().substring(1);
        System.out.println("classFileName= " + classFileNamePath);
        byte[] classBin = Utils.loadFile(classFileNamePath);
        cluster.addClass(cls.getCanonicalName() + ".class", classBin);
    }

    public static void main(String[] args) {
        SpringApplication.run(DjsClientApplication.class, args);

        Cluster cluster = new Cluster();
        cluster.addNode("http://localhost:8383");
        // add other nodes here
        // cluster.addNode("http://ip2:8383");

        try {
            // upload Task class, superclass and arguments classes to cluster
         /*   addClassToCluster(cluster, DjsTask.class);
            addClassToCluster(cluster, Math2Task.class);
            addClassToCluster(cluster, MathTaskParam.class);
            addClassToCluster(cluster, MathTaskResponse.class);*/

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

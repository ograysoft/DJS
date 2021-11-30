package com.ogray.djs.node;


import com.ogray.djs.model.*;
import com.ogray.djs.node.utils.Utils;
import com.ogray.djs.pool.RealNodeTask;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Base64;
import java.util.StringTokenizer;


@CrossOrigin
@RestController
@Slf4j
@AllArgsConstructor
public class DjsRestController {
    private com.ogray.djs.node.service.DJSService DJSService;

    @PostMapping("/addclass")
    public AddClassResponse addClass(@RequestBody AddClassRequest requestData,
                                     HttpServletResponse response) {
        log.info("addClass");

        byte[] classBin = Base64.getDecoder().decode( requestData.getData() );
        int status = 0;
        try {
            // parse class name and create folders
            String name = requestData.getName();
            log.info("class name initial: " + name);

            String pureClassName;
            // cut off '.class'
            int idx0 = name.indexOf(".class");
            if(idx0>0) {
                name = name.substring(0, idx0);
            }

            // cut off class name
            idx0 = name.lastIndexOf(".");
            pureClassName = name.substring(idx0+1) + ".class";
            log.info("pureClassName=" + pureClassName);

            name = name.substring(0, idx0);
            log.info("only packages: " + name);

            StringTokenizer tok = new StringTokenizer(name, ".");
            String folder = DjsApplication.classPoolPath;
            while(tok.hasMoreTokens()) {
                String token = tok.nextToken();
                folder += token;
                boolean ok = Utils.createFolder(folder);
                folder += "\\";
                log.info("Creating folder "+folder+" ret "+ok);
            }


            Utils.writeFile(classBin, folder + pureClassName);
        } catch (IOException e) {
            e.printStackTrace();
            status = 1;
        }
        response.setStatus(HttpStatus.OK.value());

        return new AddClassResponse(status);
    }

    /**
     * Execute task synchronousely. Wait for finish and send results back
     * @param requestData
     * @param response
     * @return AddTaskResponse task result
     */
    @PostMapping("/exectasksync")
    public AddTaskResponse addTask(@RequestBody AddTaskRequest requestData,
                                   HttpServletResponse response) {
        log.info("exectasksync");

        byte[] taskBin = Base64.getDecoder().decode( requestData.getData() );
        int status = 0;
        String result = null;
        try {
            String className = requestData.getClassName();
            int idx = className.indexOf(".class");
            if(idx>0) {
                className = className.substring(0, idx);
            }

            log.info("className=" +className);
            log.info("cp=" + System.getProperty("java.class.path"));

            File file = new File(DjsApplication.classPoolPath);

                // Convert File to a URL
                URL url = file.toURI().toURL();
                URL[] urls = new URL[]{url};

                // Create a new class loader with the directory
                ClassLoader cl = new URLClassLoader(urls);

                Class cls = cl.loadClass(className);
                log.info("loadClass ok for "+className);

                log.info("superclass: " + cls.getAnnotatedSuperclass().toString());

                Class[] cArg = new Class[1];
                cArg[0] = byte[].class;
                Method method = cls.getMethod("deserialize", cArg);
                Object task = method.invoke(null, taskBin);

               // DjsTask task = DjsTask.deserialize(taskBin);
                log.info("deserialized task class:  "+task.getClass().getName());

            //Object res = ((DjsTask)task).execute();
            Method method1 = task.getClass().getMethod("execute", null);
            Object res = method1.invoke(task, null);

            if(res!=null)
                result = res.toString();
            else
                result = "null";

        } catch (Exception e) {
            e.printStackTrace();
            status = 1;
        }

        response.setStatus(HttpStatus.OK.value());
        return new AddTaskResponse(status, result);
    }

    /**
     * Execute task asynchronously. Upon finish call callback
     * @param requestData
     * @param response
     * @return AddTaskResponse task result
     */
    @PostMapping("/exectaskasync")
    public AddAsyncTaskResponse execTaskAsync(@RequestBody AddAsyncTaskRequest requestData,
                                              HttpServletResponse response) {
        log.info("execTaskAsync");

        byte[] taskBin = Base64.getDecoder().decode( requestData.getData() );
        int status = 0;
        String result = null;
        try {
            String className = requestData.getClassName();
            int idx = className.indexOf(".class");
            if(idx>0) {
                className = className.substring(0, idx);
            }

            log.info("className=" +className);
            log.info("cp=" + System.getProperty("java.class.path"));

            File file = new File(DjsApplication.classPoolPath);

            // Convert File to a URL
            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};

            // Create a new class loader with the directory
            ClassLoader cl = new URLClassLoader(urls);

            Class cls = cl.loadClass(className);
            log.info("loadClass ok for "+className);

            log.info("superclass: " + cls.getAnnotatedSuperclass().toString());

            Class[] cArg = new Class[1];
            cArg[0] = byte[].class;
            Method method = cls.getMethod("deserialize", cArg);
            Object task = method.invoke(null, taskBin);

            // DjsTask task = DjsTask.deserialize(taskBin);
            log.info("deserialized task class:  "+task.getClass().getName());

            //Object res = ((DjsTask)task).execute();
           /* Method method1 = task.getClass().getMethod("execute", null);
            Object res = method1.invoke(task, null);

            if(res!=null)
                result = res.toString();
            else
                result = "null";*/

            Long taskId = Long.valueOf(1);
           // Long taskId = DjsApplication.taskPool.addTask(new RealNodeTask(task));
            response.setStatus(HttpStatus.OK.value());

            DjsApplication.taskPool.executeJob(task, taskId, requestData.getCallback());
            return new AddAsyncTaskResponse(0, taskId);

        } catch (Exception e) {
            e.printStackTrace();
            status = 1;
        }

        response.setStatus(HttpStatus.OK.value());
        return new AddAsyncTaskResponse(status, null);
    }
}

package com.ogray.djs.core.rest;

import com.google.gson.Gson;
import com.ogray.djs.model.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

@Slf4j
public class RestManager {
    private static String callbackUrl = "http://localhost:8384/taskupdated";

    public static AddTaskResponse execTaskSync(String url, byte[] task, String className) throws Exception {
        final String uri = url + "/exectasksync";

        AddTaskRequest request = new AddTaskRequest(className, task);
        JSONObject json = new JSONObject(request);
        String req = json.toString();
        log.info("url=["+uri+"] request="+req);
        String reply = RestClient.sendPostRequest(uri, req);

        log.info("AddTaskResponse return: "+reply);
        Gson gson = new Gson();
        AddTaskResponse obj = gson.fromJson(reply, AddTaskResponse.class);
        return obj;
    }


    public static AddClassResponse addClass(String url, String name, byte[] data) throws Exception {
        final String uri = url + "/addclass";

        AddClassRequest request = new AddClassRequest(name, data);
        JSONObject json = new JSONObject(request);
        String req = json.toString();
        log.info("url=["+uri+"] request="+req);
        String reply = RestClient.sendPostRequest(uri, req);

        log.info("AddClassResponse return: "+reply);
        Gson gson = new Gson();
        AddClassResponse obj = gson.fromJson(reply, AddClassResponse.class);
        return obj;
    }

    public static AddAsyncTaskResponse execTaskAsync(String url, byte[] task, String className) throws Exception {
        final String uri = url + "/exectaskasync";

        AddAsyncTaskRequest request = new AddAsyncTaskRequest(className, task, callbackUrl);
        JSONObject json = new JSONObject(request);
        String req = json.toString();
        log.info("url=["+uri+"] request="+req);
        String reply = RestClient.sendPostRequest(uri, req);

        log.info("AddTaskResponse return: "+reply);
        Gson gson = new Gson();
        AddAsyncTaskResponse obj = gson.fromJson(reply, AddAsyncTaskResponse.class);
        return obj;
    }
}

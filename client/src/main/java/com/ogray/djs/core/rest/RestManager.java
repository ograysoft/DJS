package com.ogray.djs.core.rest;

import com.google.gson.Gson;
import com.ogray.djs.model.AddClassRequest;
import com.ogray.djs.model.AddClassResponse;
import com.ogray.djs.model.AddTaskRequest;
import com.ogray.djs.model.AddTaskResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

@Slf4j
public class RestManager {

    public static AddTaskResponse addTask(String url, byte[] task, String className) throws Exception {
        final String uri = url + "/addtask";

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
}

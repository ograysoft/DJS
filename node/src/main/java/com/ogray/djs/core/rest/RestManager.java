package com.ogray.djs.core.rest;

import com.google.gson.Gson;
import com.ogray.djs.model.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

@Slf4j
public class RestManager {

    public static void sendCallback(String url, Long taskId, Integer status) throws Exception {

        CallbackInfo request = new CallbackInfo("", taskId, status);
        JSONObject json = new JSONObject(request);
        String req = json.toString();
        log.info("url=["+url+"] request="+req);
        RestClient.sendPostRequest(url, req);

    }
}

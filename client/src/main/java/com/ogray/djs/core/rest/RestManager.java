package com.ogray.djs.core.rest;

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

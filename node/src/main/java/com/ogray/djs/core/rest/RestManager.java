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

package com.ogray.djs.app.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Slf4j
public class DJSServiceJpa implements DJSService {

    @Autowired
    public DJSServiceJpa() {
        log.info("~~~ DJSService v1.0.0 started ~~~");

    }

    @Override
    public String getSessionId(String login) {
        return "ok";
    }

}

package com.ogray.djs.app;


import com.ogray.djs.model.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@Slf4j
@AllArgsConstructor
public class DjsRestController {
    private com.ogray.djs.app.service.DJSService DJSService;

    @PostMapping("/taskupdated")
    public void TaskUpdated(@RequestBody CallbackInfo requestData) {
        log.info("TaskUpdated "+requestData);

    }
}

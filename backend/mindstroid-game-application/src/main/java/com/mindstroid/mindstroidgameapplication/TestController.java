package com.mindstroid.mindstroidgameapplication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController {

    private static Logger audit = LogManager.getLogger("audit-log");

    @GetMapping("/first")
    public String test() {
        audit.info("one request");
        return "yes, you are here.";
    }
}

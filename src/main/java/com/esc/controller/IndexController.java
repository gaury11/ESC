package com.esc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Controller
public class IndexController {


    @GetMapping("/test")
    public @ResponseBody String test(){

        log.info("### test");

        return "test";
    }

    @GetMapping("")
    public String index(){

        log.info("### index");

        return "index";
    }

}

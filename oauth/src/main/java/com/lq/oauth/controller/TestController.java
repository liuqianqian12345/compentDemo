package com.lq.oauth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PreAuthorize("hasAuthority('test1')")
    @GetMapping("test")
    public String aa(){
        return "试验成功";
    }
}

package com.example.demo.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.HelloService;
import com.example.demo.utils.ReqBody;
import com.example.demo.utils.ResBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class HelloController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    HelloService helloService;

    @GetMapping("greeting")
    public ResBody greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new ResBody(counter.incrementAndGet(), String.format(template, name), 20);
    }

    @GetMapping
    public String home() {
        return helloService.getHello();
    }

    @PostMapping("send")
    public ResBody send(@RequestBody ReqBody reqBody) {
        System.out.printf("\n>>> ( \n");
        System.out.print(reqBody);
        System.out.printf("\n>>> ) \n");
        return new ResBody(reqBody.reqId(), reqBody.reqContent(), 40);
    }

    @DeleteMapping
    public String delete() {
        return ">>> VCN run into delete";
    }

}
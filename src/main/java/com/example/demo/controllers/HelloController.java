package com.example.demo.controllers;

import com.example.demo.configs.EnvConfig;
import com.example.demo.services.HelloService;
import com.example.demo.utils.RequestBodyDTO.*;
import com.example.demo.utils.ResponseBodyDTO.*;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private HelloService helloService;

    @Autowired
    private EnvConfig myEnv;

    @GetMapping("greeting")
    public ResBodyOneDTO greeting(
        @RequestParam(value = "myData") String data,
        @RequestParam(value = "myName", required = true) String name
    ) {
        return new ResBodyOneDTO(counter.incrementAndGet(), String.format(template, name), 20);
    }

    @GetMapping
    public String home() {
        // code more...
        return "My Life And I'm fan of Will Smith =))";
    }

    @GetMapping("lalala-okay")
    public String todo() {
        return helloService.getHello();
    }

    @PostMapping("send")
    public ResBodyOneDTO send(@RequestBody ReqBodyOneDTO reqBody) {
        System.out.printf("\n>>> ( \n");
        System.out.print(reqBody);
        System.out.printf("\n>>> ) \n");
        return new ResBodyOneDTO(reqBody.myData(), reqBody.myName(), 40);
    }

    @DeleteMapping
    public Success delete() {
        System.out.printf(">>> env 1 info: %s \n", myEnv.getRssEnv1());
        System.out.printf(">>> env 2 info: %s \n", myEnv.getRssEnv2() + 55);
        System.out.printf(">>> env 3 info: %s \n", myEnv.getRssEnv3());
        return new Success();
    }

    @PostMapping("test-response-body-one")
    public Success testResponseBodyOne(@RequestBody ReqBodyOneDTO reqBody) {
        System.out.printf("\n>>> ( \n");
        System.out.print(reqBody);
        System.out.printf("\n>>> ) \n");
        return new Success();
    }

    @PostMapping("test-response-body-two")
    public Success testResponseBodyTwo(@RequestBody ReqBodyTwoDTO reqBody) {
        System.out.printf("\n>>> ( \n");
        System.out.print(reqBody);
        System.out.printf("\n>>> ) \n");
        return new Success();
    }

    @PostMapping("test-response-body-three")
    public Success testResponseBodyThree(@RequestBody ReqBodyThreeDTO reqBody) {
        System.out.printf("\n>>> ( \n");
        System.out.print(reqBody);
        System.out.printf("\n>>> ) \n");
        return new Success();
    }

    @GetMapping("success")
    public Success success() {
        return new Success();
    }

    @GetMapping("param/{paramID}")
    public Success pathVariable(@PathVariable("paramID") int paramID) {
        System.out.printf(">>> paramID: %d \n", paramID);
        return new Success("VCN path variable ID: " + paramID);
    }
}

package com.example.demo.utils;

public class RequestBodyDTO {

    public record ReqBodyOneDTO(int myData, String myName) {}

    public record ReqBodyTwoDTO(int reqId, String reqContent, String something) {}

    public record ReqBodyThreeDTO(String reqId, String reqContent, int IDcard) {}
}
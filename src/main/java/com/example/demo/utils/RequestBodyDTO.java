package com.example.demo.utils;

public class RequestBodyDTO {

    public record ReqBodyOneDTO(long reqId, String reqContent) {}

    public record ReqBodyTwoDTO(int reqId, String reqContent, String something) {}

    public record ReqBodyThreeDTO(String reqId, String reqContent, int IDcard) {}
}

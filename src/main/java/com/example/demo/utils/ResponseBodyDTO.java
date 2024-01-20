package com.example.demo.utils;

public class ResponseBodyDTO {

    public record ResBodyOneDTO(long id, String content, int num) {}

    public record ResBodyTwoDTO(int id, String content, int num, int value) {}

    public record ResBodyThreeDTO(int id, String content, int num, String val) {}

    public static class Success {

        String status = "success";

        public Success() {}

        public Success(String message) {
            this.status = message;
        }

        public String getStatus() {
            return status;
        }
    }
}

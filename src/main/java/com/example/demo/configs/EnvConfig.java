package com.example.demo.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "self-def")
public class EnvConfig {

    private String rssEnv1;
    private int rssEnv2;
    private String rssEnv3;
    private int clientPort; 

    public String getRssEnv1() {
        return rssEnv1;
    }

    public int getRssEnv2() {
        return rssEnv2;
    }

    public String getRssEnv3() {
        return rssEnv3;
    }

    public void setRssEnv1(String rssEnv1) {
        this.rssEnv1 = rssEnv1;
    }

    public void setRssEnv2(int rssEnv2) {
        this.rssEnv2 = rssEnv2;
    }

    public void setRssEnv3(String rssEnv3) {
        this.rssEnv3 = rssEnv3;
    }

    public int getClientPort() {
        return clientPort;
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }
}

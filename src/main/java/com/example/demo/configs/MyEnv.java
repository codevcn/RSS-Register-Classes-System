package com.example.demo.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "rssenv")
public class MyEnv {

    String rssEnv1;
    String rssEnv2;
    String rssEnv3;

    public String getRssEnv1() {
        return rssEnv1;
    }

    public String getRssEnv2() {
        return rssEnv2;
    }

    public String getRssEnv3() {
        return rssEnv3;
    }

    public void setRssEnv1(String rssEnv1) {
        this.rssEnv1 = rssEnv1;
    }

    public void setRssEnv2(String rssEnv2) {
        this.rssEnv2 = rssEnv2;
    }

    public void setRssEnv3(String rssEnv3) {
        this.rssEnv3 = rssEnv3;
    }

}

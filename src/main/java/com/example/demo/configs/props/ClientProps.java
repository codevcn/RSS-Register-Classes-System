package com.example.demo.configs.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "rss.client")
@Getter
@Setter
public class ClientProps {

    private int port;
    private String host;
}

package com.example.woong.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("naver")
@Getter
@Setter
public class APIAuthProperties {
    private String clientId;
    private String clientSecret;
}

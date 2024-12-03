package com.example.woong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;


import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebFluxConfig {
    
    @Bean
    public static HttpClient defaultHttpClient() {
        HttpClient client = HttpClient
                .create()
                .doOnConnected(con -> con
                        .addHandlerFirst(
                                new ReadTimeoutHandler(3000))
                        .addHandlerLast(
                                new WriteTimeoutHandler(3000)))
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000);
        return client;
    }

    public static WebClient webClient(HttpClient httpClient){
        return WebClient
        .builder()
        .clientConnector(new ReactorClientHttpConnector(httpClient))
        .build();
    }

    @Bean
    public static WebClient defaultWebClient(HttpClient httpClient){
        return webClient(httpClient);
    }
}

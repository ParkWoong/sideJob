package com.example.woong.service.common;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.woong.properties.APIAuthProperties;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private static final ParameterizedTypeReference<Map<String, Object>> MAP_TYPE = new ParameterizedTypeReference<Map<String,Object>>() {};
    
    private final WebClient webClient;
    private final static String CLIENTID = "X-Naver-Client-Id";
    private final static String CLIENTSECRET = "X-Naver-Client-Secret";
    private final APIAuthProperties apiAuthProperties;


    public ResponseEntity<?> exchange(final String url, final MultiValueMap<String, String> query){
        
        // 응답 데이터를 block()을 통해 동적으로 가져옴
        Map<String, Object> responseData =  webClient
                .get()
                .uri(url, uriBuilder ->uriBuilder.queryParams(query).build())
                .header(CLIENTID, apiAuthProperties.getClientId())
                .header(CLIENTSECRET, apiAuthProperties.getClientSecret())
                .exchangeToMono(response -> {
                    return response.bodyToMono(MAP_TYPE);
                })
                .onErrorResume(e->{
                    e.printStackTrace();
                    return Mono.empty();}
                )
                .block();

        return ResponseEntity.ok(responseData);
    }
}

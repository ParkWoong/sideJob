package com.example.woong.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import com.example.woong.config.SeleniumConfig;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DynamicCrolling {
    
    private final SeleniumConfig seleniumConfig;

    public String getDynamicCrolling(final String url){
        
        WebDriver driver = seleniumConfig.getWebDriver();

        driver.get(url);

        WebElement dynamiElement = driver.findElement(By.id("dynamic-content-id"));


        return null;
    }


}

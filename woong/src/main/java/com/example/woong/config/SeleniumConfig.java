package com.example.woong.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Configuration;

import io.github.bonigarcia.wdm.WebDriverManager;

@Configuration
public class SeleniumConfig {
    public WebDriver getWebDriver(){
        WebDriverManager.chromedriver().setup();

        //Chrome 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); //브라우저 UI 없이 실행
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        return new ChromeDriver(options);
    }
}

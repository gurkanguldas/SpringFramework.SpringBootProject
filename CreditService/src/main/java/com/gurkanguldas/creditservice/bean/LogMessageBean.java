package com.gurkanguldas.creditservice.bean;

import com.gurkanguldas.creditservice.business.service.message.LogMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogMessageBean {
    @Bean
    public LogMessage logMessage()
    {
        return new LogMessage();
    }
}

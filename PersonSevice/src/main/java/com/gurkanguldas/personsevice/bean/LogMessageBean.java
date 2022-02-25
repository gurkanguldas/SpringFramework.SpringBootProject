package com.gurkanguldas.personsevice.bean;

import com.gurkanguldas.personsevice.business.service.message.LogMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogMessageBean {
    @Bean
    public  LogMessage logMessage()
    {
        return new LogMessage();
    }
}

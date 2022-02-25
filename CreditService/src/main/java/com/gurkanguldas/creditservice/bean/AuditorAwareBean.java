package com.gurkanguldas.creditservice.bean;

import com.gurkanguldas.creditservice.audit.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
public class AuditorAwareBean {

    @Bean
    public AuditorAware<String> auditorAware()
    {
        return new AuditorAwareImpl();
    }
}
